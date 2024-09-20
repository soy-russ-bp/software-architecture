package ada5;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SortingLayerImpl implements SortingLayer{
    private final Locale LOCALE_ES = new Locale.Builder().setLanguage("es").build();
    private List<String> mySortedList;

    private List<String> sortList(List<String> list){
        System.out.println("sorting ...");
        Collator primaryCollator = Collator.getInstance(LOCALE_ES);
        primaryCollator.setStrength(Collator.PRIMARY);
        List<String> sortedWords = new ArrayList<>(list);
        sortedWords.sort(primaryCollator);
        return sortedWords;
    }

    @Override
    public void KWICHandler(List<String> variationsList) {
        List<String> sortedList = sortList(variationsList);
        this.mySortedList = sortedList;
    }
    @Override
    public List<String> bringList() {
        System.out.println("bringing list 1/3 ...");
        return this.mySortedList;
    }
}
