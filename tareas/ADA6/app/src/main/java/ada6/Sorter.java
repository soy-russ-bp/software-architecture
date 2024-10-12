package ada6;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Sorter extends Filter{
    private final Locale LOCALE_ES = new Locale.Builder().setLanguage("es").build();

    public Sorter(Pipe pipe) {
        super(pipe);
    }

    @Override
    protected Object action(Object input) {
        System.out.println("sorting");
        
        List<String> list = (List<String>)input;
        System.out.println("sorting ...");
        Collator primaryCollator = Collator.getInstance(LOCALE_ES);
        primaryCollator.setStrength(Collator.PRIMARY);
        List<String> sortedWords = new ArrayList<>(list);
        sortedWords.sort(primaryCollator);
        return sortedWords;
    }

}
