package infrastructure;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
private List<String> lines = new ArrayList<>();
    private String fileName = "Nombres.txt";

    public List<String> readerToList(){

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display lines readed, not necessary
        for (String line : lines) {
            System.out.println(line);
        }


        return lines;
    }

    public List<String> getList(){
        return lines;
    }

    public void setFileName(String newFileName){
        fileName = newFileName;
    }
    

}
