package com.manics.rest.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import org.openimaj.image.ImageUtilities;
import org.openimaj.image.pixel.statistics.HistogramModel;
import org.openimaj.math.statistics.distribution.MultidimensionalHistogram;
import org.springframework.stereotype.Service;

@Service
public class AnalyzerImageService {

    public Double[] analyzeImageFormUrl(String urlImage) throws IOException {
        URL imageUrl = new URL(urlImage);
        MultidimensionalHistogram histogram;
        HistogramModel model = new HistogramModel(50);
        model.estimateModel(ImageUtilities.readMBF(imageUrl));
        histogram = model.histogram.clone();
        Double[] res = Arrays.stream(histogram.values).boxed().toArray(Double[]::new);
        return res;
    }
}
