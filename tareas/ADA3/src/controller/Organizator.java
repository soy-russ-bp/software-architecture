package controller;
import java.util.ArrayList ;

public class Organizator {
    // instancias:
    //View presentator=new View;
    //Reader reader = new Reader; (getNames)
    

    // Metodos
    private String formatName(String name) {
        StringBuilder formattedName = new StringBuilder();
    
        for(int i = 0; i < name.length(); i++) {
            String letter;
            boolean isFirstChar = i == 0;
            boolean isPreviousCharSpace = isFirstChar ? false : name.charAt(i - 1) == ' ';
            boolean isCharSpace=name.charAt(i) == ' ';

            if (isFirstChar || (isPreviousCharSpace)) {
                letter = name.substring(i, i + 1).toUpperCase();
            } else if (isCharSpace) {
                letter = " ";
            } else {
                letter = name.substring(i, i + 1).toLowerCase();
            }
            formattedName.append(letter);
        }
        return formattedName.toString();
    }
    

    public ArrayList<String> formatNames(ArrayList<String> names){
        ArrayList<String> namesWithFormat = new ArrayList<String>();;

        for(String name: names){
            String newName= this.formatName(name);
            namesWithFormat.add(newName);
        }
        return namesWithFormat;
    }

   
    public ArrayList<String> ascendendSort(ArrayList<String> names) {
        ArrayList<String> sortedNames = new ArrayList<String>(names);
        sortedNames.sort(null);
        return sortedNames;
    }
     
    
}