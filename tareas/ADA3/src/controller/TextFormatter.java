package controller;

import java.util.ArrayList;
import java.util.List;

public class TextFormatter {
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
    

    public ArrayList<String> formatNames(List<String> names){
        ArrayList<String> namesWithFormat = new ArrayList<String>();;

        for(String name: names){
            String newName= this.formatName(name);
            namesWithFormat.add(newName);
        }
        return namesWithFormat;
    }

}
