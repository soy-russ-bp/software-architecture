import java.util.ArrayList;

import controller.Organizator;
import infrastructure.TextFileLinesDatasourceImpl;

public class Main {

    public static void main(String[] args) {
        Organizator organizator = new Organizator();

        TextFileLinesDatasourceImpl namesRepository = new TextFileLinesDatasourceImpl();

        ArrayList<String> newNames = organizator.formatNames(namesRepository.getLines());

        ArrayList<String> orderedNames = organizator.ascendendSort(newNames);

        for (String name : orderedNames) {
            System.out.println(name);
        }

        
        
    }
}