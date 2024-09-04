package view;

import java.util.ArrayList;


public class View {

	public void showNames(ArrayList<String> names) {

        for (String name : names) {
            System.out.println(name);
        }
	}
}