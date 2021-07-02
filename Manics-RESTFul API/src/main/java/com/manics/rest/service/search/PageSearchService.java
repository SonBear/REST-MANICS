package com.manics.rest.service.search;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.ElasticSearch.PageSearch;
import com.manics.rest.repository.elasticsearch.PageSearchRepository;
import com.manics.rest.service.AnalyzerImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PageSearchService {

    public static final String INDEX_PAGE_SEARCH = "pages_searching";
    private final PageSearchRepository pageSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;
    private final AnalyzerImageService analyzerImageService;

    @Autowired
    public PageSearchService(PageSearchRepository pageSearchRepository, ElasticsearchOperations elasticsearchOperations,
            AnalyzerImageService analyzerImageService) {
        this.pageSearchRepository = pageSearchRepository;
        this.elasticsearchOperations = elasticsearchOperations;
        this.analyzerImageService = analyzerImageService;
    }

    @Async
    public PageSearch saveSearchPage(Integer id, String url) {
        PageSearch pageSearch = new PageSearch();
        pageSearch.setPageId(id);
        pageSearch.setUrlImage(url);
        Double[] response;
        try {
            response = analyzerImageService.analyzeImageFormUrl(url);
        } catch (IOException e) {
            throw new RuntimeException("Error al analizar la imagen / no se proporciono un url valido");
        }
        pageSearch.setVector(response);
        return pageSearchRepository.save(pageSearch);
    }

    public PageSearch deletePageSearch(Integer id) {
        PageSearch pageSearch = getPageSearch(id);
        pageSearchRepository.delete(pageSearch);
        return pageSearch;
    }

    public PageSearch getPageSearch(Integer id) {
        return pageSearchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aún no se ha registrado ese pagina"));
    }

    @Async
    public PageSearch updatePageSearch(Integer id, String url) {
        PageSearch pageSearch = getPageSearch(id);
        pageSearch.setUrlImage(url);
        Double[] vector;
        try {
            vector = analyzerImageService.analyzeImageFormUrl(url);
        } catch (IOException e) {
            throw new RuntimeException("Error al analizar la imagen / no se proporciono un url valido");
        }
        pageSearch.setVector(vector);
        pageSearchRepository.save(pageSearch);
        return pageSearch;
    }

    public List<PageSearch> scoreByDenseVector(Double[] vector) {
        StringQuery query = new StringQuery(
                "{\"function_score\":{\"query\":{\"match_all\":{}},\"script_score\":{\"script\":{\"source\":\"1 / (1 + l2norm(params.query_vector, 'vector_values'))\",\"params\":{\"query_vector\":"
                        + Arrays.toString(vector) + "}}},\"score_mode\":\"max\"}}");

        SearchHits<PageSearch> tests = elasticsearchOperations.search(query, PageSearch.class,
                IndexCoordinates.of(INDEX_PAGE_SEARCH));

        return tests.get().map((e) -> e.getContent()).collect(Collectors.toList());
    }
}
