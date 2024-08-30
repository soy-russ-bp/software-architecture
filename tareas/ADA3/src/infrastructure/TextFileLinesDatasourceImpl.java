package infrastructure;

import java.util.List;

import domain.LinesDatasource;

public class TextFileLinesDatasourceImpl implements LinesDatasource{

    private Reader reader = new Reader();

    @Override
    public List<String> getLines() {
        return reader.getList();
    }
    
}
