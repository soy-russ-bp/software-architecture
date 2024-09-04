package controller;

import java.util.List;
import java.util.ArrayList;


import view.View;
import repositoryLayer.domain.LinesDatasource;
import repositoryLayer.infrastructure.TextFileLinesDatasourceImpl;

public class Controller {

    private LinesDatasource namesRepository = new TextFileLinesDatasourceImpl();
    private View view;
    private TextFormatter textFormatter;
    private ListSorter sorter;


    public Controller( LinesDatasource namesRepository, TextFormatter textFormatter, ListSorter sorter,  View view) {
        this.namesRepository = namesRepository;
        this.textFormatter = textFormatter;
        this.sorter = sorter;
        this.view = view;
    }

    public void run() {

        List<String> names= namesRepository.getLines();
        ArrayList<String> Names = textFormatter.formatNames(names);
        ArrayList<String> orderedNames = sorter.ascendendSort(Names);
        view.showNames(orderedNames);
    }
    
}