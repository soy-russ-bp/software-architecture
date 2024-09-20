package ada5;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        System.out.println(noEmptyWords);
        return noEmptyWords;
    }
    private String lowerCaser(String string) {
        System.out.println("lower casing ...");
        String lowerCased = string.toLowerCase();
        return lowerCased;
    }
    private String emptyWords(String lowerCased) {
        System.out.println("deleting empty words ...");

        String[] words = lowerCased.split("\\s+"); // Divide el string en palabras, convirtiendo a minúsculas
        StringBuilder result = new StringBuilder();
        
        Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "and", "or", "but", "on", "in", "with", "is", "it", "this", "that", "to", "for", "of"
        ));

        for (String word : words) {
            if (!STOP_WORDS.contains(word)) {
                result.append(word).append(" ");
            }
        }
        
        // Devolver la cadena sin las palabras vacías
        return result.toString().trim();
    }

    @Override
    public List<String> bringList() {
        System.out.println("bringing list 3/3 ...");

        return variationGenerator.bringList();
    }

}
