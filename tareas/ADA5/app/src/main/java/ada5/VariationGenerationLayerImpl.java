package ada5;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<String> generateVariations(String string) {

        List<String> words = new ArrayList<>(Arrays.asList(string.split(" ")));
        String originalString = String.join(" ", words);

        List<String> variationsList = new ArrayList<>();
        variationsList.add(originalString);

        String temporalString = "";
        while (!temporalString.equals(originalString)) {
    
            String firstWord = words.remove(0);
            words.add(firstWord);

            temporalString = String.join(" ", words);
            
            if (!newString.equals(originalString)) {
                variatioansList.add(temporalString);
            }
        }
        
        return variationsList;
    }
    
    @Override
    public List<String> bringList() {
        System.out.println("bringing list 2/3 ...");

        return sortingLayer.bringList();
    }
}
