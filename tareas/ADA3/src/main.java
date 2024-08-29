import java.util.ArrayList;

import controller.Organizator;

public class main {

    public static void main(String[] args) {
        Organizator o = new Organizator();
        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add("vIcTor huGo");
        nombres.add("peDRo PaRamO");
        nombres.add("jUAn rUlFo");

        ArrayList<String> newNames = o.formatNames(nombres);

        ArrayList<String> orderedNames = o.ascendendSort(newNames);

        for( String name: orderedNames){
            System.out.println(name);
        }
        

        
    }
}