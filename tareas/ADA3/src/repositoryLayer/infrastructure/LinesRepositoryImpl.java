package repositoryLayer.infrastructure;

import java.util.List;

import repositoryLayer.domain.LinesDatasource;
import repositoryLayer.domain.LinesRepository;

public class LinesRepositoryImpl implements LinesRepository {

    private LinesDatasource datasource;

    public LinesRepositoryImpl(LinesDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<String> getLines() {
        return datasource.getLines();
    }

}
