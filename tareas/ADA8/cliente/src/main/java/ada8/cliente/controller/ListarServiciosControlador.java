package ada8.cliente.controller;

import java.util.ArrayList;

import ada8.cliente.data_model.ListaServicios;
import ada8.cliente.observer.IObservador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListarServiciosControlador implements IObservador {

    private class ItemServicio {
        private final SimpleStringProperty servicio;

        public ItemServicio(String servicio) {
            this.servicio = new SimpleStringProperty(servicio);
        }

        public SimpleStringProperty getServicio() {
            return servicio;
        }
    }
    @FXML
    private TableView<ItemServicio> servicios;
    @FXML
    private TableColumn<ItemServicio, String> serviciosCol;

    @FXML
    private TextField palabraInput;

    public void init() {

        crearCabecera();

    }

    public void buscarServicios() {
        String palabra = palabraInput.getText();
        try {
            ListaServicios.listarServicios(palabra);

            serviciosCol.setCellValueFactory(cellData -> cellData.getValue().getServicio());
            ArrayList<ItemServicio> data = new ArrayList<>();
            for (String servicio : ListaServicios.obtenerServicios()) {
                data.add(new ItemServicio(servicio));

            }

            servicios.getItems().clear();
            servicios.getItems().setAll(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Label crearEtiquetaRegistro(String registro) {
        Label etiqueta = new Label();
        etiqueta.setStyle("-fx-font-size: 16px;");
        return etiqueta;
    }

    private void crearCabecera() {
        Label evento = new Label("Total de servicios: " + ListaServicios.obtenerTotalServicios());
        evento.setStyle("-fx-font-size: 16px;");
        evento.setPrefWidth(200);
        evento.setWrapText(true);

        Button boton = new Button("Actualizar");

    }

    @Override
    public void actualizarVista() {

        this.init();
    }
}
