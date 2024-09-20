package ada5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StopWords {

    //private String filePath = "app/src/main/resources/StopWordsSpanish.txt"; // Cambia la ruta al archivo
    private String filePath = "C:/Users/luism/OneDrive - Universidad Autonoma de Yucatan/Semestre V/Arquitecturas de software/Names/tareas/ADA5/app/src/main/resources/StopWordsSpanish.txt"; // Cambia la ruta al archivo
    private Set<String> stopwords = new HashSet<>();

    public Set<String> getStopWords(){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
