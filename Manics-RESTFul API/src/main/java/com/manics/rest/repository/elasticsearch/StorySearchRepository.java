package com.manics.rest.repository.elasticsearch;

import java.util.List;

import com.manics.rest.model.core.ElasticSearch.StorySearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorySearchRepository extends ElasticsearchRepository<StorySearch, Integer> {
    public List<StorySearch> findByNameLike(String name);
}
