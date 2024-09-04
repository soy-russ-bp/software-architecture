
import view.View;
import repositoryLayer.domain.LinesRepository;
import repositoryLayer.infrastructure.LinesRepositoryImpl;
import repositoryLayer.infrastructure.TextFileLinesDatasourceImpl;
import controller.TextFormatter;
import controller.ListSorter;
import controller.Controller;

public class Main {

    public static void main(String[] args) {

        View view = new View();
        LinesRepository namesRepository = new LinesRepositoryImpl(new TextFileLinesDatasourceImpl());
        TextFormatter textFormatter = new TextFormatter();
        ListSorter sorter = new ListSorter();
        Controller controller = new Controller(namesRepository, textFormatter, sorter, view);

        controller.run();

    }
}