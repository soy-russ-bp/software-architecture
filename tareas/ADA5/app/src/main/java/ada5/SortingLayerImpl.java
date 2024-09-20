package ada5;

import java.util.List;

public class SortingLayerImpl implements SortingLayer{
    private List<String> mySortedList;

    @Override
    public void KWICHandler(List<String> variationsList) {
        List<String> sortedList = sortList(variationsList);
        this.mySortedList = sortedList;
    }

    private List<String> sortList(List<String> list){
        System.out.println("sorting ...");
        List<String> sorted = list;
        return sorted;
    }

    @Override
    public List<String> bringList() {
        System.out.println("bringing list 1/3 ...");
        return this.mySortedList;
    }


}
