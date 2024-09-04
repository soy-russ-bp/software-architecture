package controller;
import java.util.ArrayList ;
import java.util.List;

public class ListSorter {
   
    public ArrayList<String> ascendendSort(List<String> names) {
        ArrayList<String> sortedNames = new ArrayList<String>(names);
        sortedNames.sort(null);
        return sortedNames;
    }   
    
}