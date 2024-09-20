package ada5;

import java.util.List;

public class App {

    public static void main(String[] args) {
        SortingLayer sortingLayer = new SortingLayerImpl();
        VariationGenerationLayer variationGenerationLayer = new VariationGenerationLayerImpl(sortingLayer);
        NormalizationLayer normalizationLayer = new NormalizationLayerImpl(variationGenerationLayer);

        String stringKwic = "qwSLKNDr a asUEDNdf for vUQWDUNxnzc";

        normalizationLayer.KWICHandler(stringKwic);
        List<String> finalList = normalizationLayer.bringList();
    }
}