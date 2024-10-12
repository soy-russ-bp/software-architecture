package ada6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VariationGenerator extends Filter{

    public VariationGenerator(Pipe pipe){
        super(pipe);
    }
    
    @Override
    protected Object action(Object input) {
        System.out.println("generatin variations");
        
        String string = (String)input;
        List<String> words = new ArrayList<>(Arrays.asList(string.split(" ")));
        String originalString = String.join(" ", words);
        List<String> variationsList = new ArrayList<>();
        variationsList.add(originalString);
        String temporalString = "";
        while (!temporalString.equals(originalString)) {    
            String firstWord = words.remove(0);
            words.add(firstWord);
            temporalString = String.join(" ", words);
            if (!temporalString.equals(originalString)) {
                variationsList.add(temporalString);
            }
        }
        
        return variationsList;
    }
    
}
