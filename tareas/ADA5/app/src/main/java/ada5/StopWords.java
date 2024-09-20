package ada5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class StopWords {

    //private String filePath = "app/src/main/resources/StopWordsSpanish.txt"; // Cambia la ruta al archivo
    InputStream inputFile = getClass().getResourceAsStream("/StopWordsSpanish.txt");
    private Set<String> stopwords = new HashSet<>();

    public Set<String> getStopWords(){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dividir por espacios en caso de que haya varias palabras en una línea
                String[] words = line.split("\\s+");
                for (String word : words) {
                    stopwords.add(word.toLowerCase().trim()); // Almacenar en minúsculas y sin espacios extra
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopwords;
    }

}
