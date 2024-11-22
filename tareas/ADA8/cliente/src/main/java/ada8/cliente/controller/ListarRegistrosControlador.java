package ada8.cliente.controller;

import ada8.cliente.data_model.ListaRegistros;
import ada8.cliente.observer.IObservador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListarRegistrosControlador implements IObservador {

    @FXML
    private VBox cajaVerticalRegistros;
    private HBox cajaHorizontalCabecera;

    public void init() {
        crearCabecera();
        for (String registro : ListaRegistros.obtenerRegistros()) {
            Label registroEtiqueta = crearEtiquetaRegistro(registro);
            cajaVerticalRegistros.getChildren().add(registroEtiqueta);
        }
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
