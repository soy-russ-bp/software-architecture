package ada5;

import java.util.List;

public class VariationGenerationLayerImpl implements VariationGenerationLayer{
    private SortingLayer sortingLayer;
    private List<String> listWithVariations;
    public VariationGenerationLayerImpl(SortingLayer sortingLayer){
        this.sortingLayer = sortingLayer;
    }
    @Override
    public void KWICHandler(String normalizedString) {
        List<String> variationList = generateVariations(normalizedString);
        this.listWithVariations = variationList;
        sortingLayer.KWICHandler(variationList);
    }

    private List<String> generateVariations (String string){
        System.out.println("generating variations");
        List<String> variationsList = null;
        variationsList.add(string);
        return variationsList;
    }

    @Override
    public List<String> bringList() {
        System.out.println("bringing list 2/3 ...");

        return sortingLayer.bringList();
    }
}
