package ada8.cliente.controller;

import java.util.ArrayList;

import ada8.cliente.data_model.ListaRegistros;
import ada8.cliente.observer.IObservador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListarRegistrosControlador implements IObservador {

    private class ItemEvento {
        private final SimpleStringProperty evento;

        public ItemEvento(String evento) {
            this.evento = new SimpleStringProperty(evento);
        }

        public SimpleStringProperty getevento() {
            return evento;
        }
    }

    @FXML
    private VBox cajaVerticalRegistros;
    @FXML
    private HBox cajaHorizontalCabecera;
    @FXML
    private TableView<ItemEvento> eventos;
    @FXML
    private TableColumn<ItemEvento, String> eventosCol;

    public void init() {
        crearCabecera();
        eventosCol.setCellValueFactory(cellData -> cellData.getValue().getevento());
        ArrayList<ItemEvento> data = new ArrayList<>();
        for (String registro : ListaRegistros.obtenerRegistros()) {
            data.add(new ItemEvento(registro));

        }

        eventos.getItems().clear();
        eventos.getItems().setAll(data);

    }

    private Label crearEtiquetaRegistro(String registro) {
        Label etiqueta = new Label();
        etiqueta.setStyle("-fx-font-size: 16px;");
        return etiqueta;
    }

    private void crearCabecera() {
        Label evento = new Label("Total de eventos: " + ListaRegistros.obtenerTotalEventos());
        evento.setStyle("-fx-font-size: 16px;");
        evento.setPrefWidth(200);
        evento.setWrapText(true);
        cajaHorizontalCabecera.getChildren().add(evento);

        Button boton = new Button("Actualizar");
        cajaHorizontalCabecera.getChildren().add(boton);
        boton.setOnAction(e -> {
            cajaVerticalRegistros.getChildren().clear();
            cajaHorizontalCabecera.getChildren().clear();
            this.init();
        });
    }

    @Override
    public void actualizarVista() {
        cajaVerticalRegistros.getChildren().clear();
        cajaHorizontalCabecera.getChildren().clear();
        this.init();
    }
}
