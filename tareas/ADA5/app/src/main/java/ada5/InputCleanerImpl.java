package ada5;

import java.util.List;
import java.util.Set;

public class InputCleanerImpl implements InputCleaner{
    private VariationGenerationLayer variationGenerator;
    public InputCleanerImpl(VariationGenerationLayer variationGenerator){
        this.variationGenerator = variationGenerator;
    }
    @Override
    public void KWICHandler(String text) {
        String normalizedString = clean(text);
        System.out.println(normalizedString);
        
        variationGenerator.KWICHandler(normalizedString);
    }

    private String clean(String lowerCased) {
        System.out.println("deleting empty words ...");

        //String[] words = lowerCased.split("\\s+"); // Divide el string en palabras, convirtiendo a minúsculas
        String[] words = lowerCased.toLowerCase().split("\\s+"); // Divide el string en palabras, convirtiendo a minúsculas

        StringBuilder result = new StringBuilder();
        
        StopWords stopWords = new StopWords();
        Set<String> STOP_WORDS = stopWords.getStopWords();

        for (String word : words) {
            if (!STOP_WORDS.contains(word)) {
                result.append(word).append(" ");
            }
        }
        
        // Devolver la cadena sin las palabras vacías
        String resultString = result.toString().trim();
        resultString = resultString.toLowerCase();
        return resultString;
    }

    @Override
    public List<String> bringList() {
        System.out.println("bringing list 3/3 ...");

        return variationGenerator.bringList();
    }

}
