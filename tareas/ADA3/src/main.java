
import view.View;
import repositoryLayer.domain.LinesDatasource;
import repositoryLayer.infrastructure.TextFileLinesDatasourceImpl;
import controller.TextFormatter;
import controller.ListSorter;
import controller.Controller;

public class Main {

    public static void main(String[] args) {

        View view = new View();
        LinesDatasource namesRepository = new TextFileLinesDatasourceImpl();
        TextFormatter textFormatter = new TextFormatter();
        ListSorter sorter = new ListSorter();
        Controller controller = new Controller(namesRepository, textFormatter, sorter, view);

        controller.run();

    }
}