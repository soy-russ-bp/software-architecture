package ada5;

import java.util.List;

public class NormalizationLayerImpl implements NormalizationLayer{
    private VariationGenerationLayer variationGenerator;
    private String myNormalizedString;
    public NormalizationLayerImpl(VariationGenerationLayer variationGenerator){
        this.variationGenerator = variationGenerator;
    }
    @Override
    public void KWICHandler(String text) {
        String normalizedString = normalize(text);
        this.myNormalizedString = normalizedString;
        variationGenerator.KWICHandler(normalizedString);
    }

    private String normalize(String string){
        System.out.println("normalizing ...");
        String lowerCased = lowerCaser(string);
        String noEmptyWords = emptyWords(lowerCased);
        return noEmptyWords;
    }
    private String lowerCaser(String string) {
        System.out.println("lower casing ...");
        String lowerCased = string;
        return lowerCased;
    }
    private String emptyWords(String lowerCased) {
        System.out.println("deleting empty words ...");
        String noEmptyWords = lowerCased;
        return noEmptyWords;
    }

    @Override
    public List<String> bringList() {
        System.out.println("bringing list 3/3 ...");

        return variationGenerator.bringList();
    }

}
