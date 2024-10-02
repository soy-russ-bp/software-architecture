package ada6;

import java.io.IOException;
import java.util.Set;

public class InputCleaner extends Filter{
    public InputCleaner(Pipe pipe) {
        super(pipe);
    }

    @Override
    protected Object action(Object input) {
        System.out.println("deleting empty words ...");
        
        String stringInput = (String)input;
        String[] words = stringInput.toLowerCase().split("\\s+");

        StringBuilder result = new StringBuilder();
        
        StopWords stopWords = new StopWords();

        Set<String> STOP_WORDS = null;
        try {
            STOP_WORDS = stopWords.getStopWords();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (String word : words) {
            if (!STOP_WORDS.contains(word)) {
                result.append(word).append(" ");
            }
        }
        
        String resultString = result.toString().trim();
        resultString = resultString.toLowerCase();
        return resultString;
    }

}
