package infrastructure;

import java.util.List;

import domain.LinesDatasource;
import domain.LinesRepository;

public class LinesRepositoryImpl implements LinesRepository {

    private LinesDatasource datasource;

    @Override
    public List<String> getLines() {
        return datasource.getLines();
    }

}
