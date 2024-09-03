package view;

import java.util.ArrayList;

import controller.Organizator;
import infrastructure.TextFileLinesDatasourceImpl;

public class OrganizatorView {
	private static Organizator organizator = new Organizator();

	public void runOrganizator() {

        TextFileLinesDatasourceImpl namesRepository = new TextFileLinesDatasourceImpl();

        ArrayList<String> newNames = organizator.formatNames(namesRepository.getLines());

        ArrayList<String> orderedNames = organizator.ascendendSort(newNames);

        for (String name : orderedNames) {
            System.out.println(name);
        }
	}
}
