package ada6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class StopWords {

    //InputStream inputFile = getClass().getResourceAsStream("StopWordsSpanish.txt");
    private Set<String> stopwords = new HashSet<>();

    
    public Set<String> getStopWords() throws IOException{
        InputStream inputFile = getClass().getClassLoader().getResourceAsStream("StopWordsSpanish.txt");
        if (inputFile == null) {
            throw new FileNotFoundException("StopWordsSpanish.txt not found in the classpath");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    stopwords.add(word.toLowerCase().trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopwords;
    }

}
