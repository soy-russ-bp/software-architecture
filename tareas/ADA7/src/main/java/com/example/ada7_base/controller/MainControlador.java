package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainControlador {

    private static final Logger registrador = LogManager.getLogger(MainControlador.class);
    private static final double localizadorHorizontal = 400;
    private static final double localizadorVertical = 912;

    @FXML
    private VBox cajaVerticalProductos;
    private Stage escenarioPastel;
    private Stage escenarioBarras;

    private static ListaProductos listaProductos = new ListaProductos();
    Map<String, Label> etiquetasVotos = new HashMap<>();
    private GraficaPastelControlador controladorPastel;
    private GraficaBarrasControlador controladorBarras;

    @FXML
    public void initialize() {
        for (Producto producto : listaProductos.obtenerProductos()) {
            ImageView imagenVista = this.crearImagenProducto(producto);
            cajaVerticalProductos.getChildren().add(imagenVista);

            Label conteo = this.crearEtiquetaVotos(producto);
            cajaVerticalProductos.getChildren().add(conteo);

            Button boton = this.crearBotonVoto(producto);
            cajaVerticalProductos.getChildren().add(boton);
        }

        Button btnPastel = this.crearBotonGrafica("Gráfica de pastel");
        Button btnBarras = this.crearBotonGrafica("Gráfica de barras");

        HBox cajaHorizontalBoton = this.crearContenedorDeBotonesDeGraficas(10, btnPastel, btnBarras);

        cajaVerticalProductos.getChildren().add(cajaHorizontalBoton);

        cajaVerticalProductos.setAlignment(Pos.TOP_CENTER);

        registrador.info("Vista principal inicializada");

        this.escenarioPastel = new Stage();
        this.escenarioBarras = new Stage();
    }

    void mostrarPastel() throws IOException {
        registrador.info("Mostrando gráfica de pastel");

        if (this.escenarioPastel == null || !this.escenarioPastel.isShowing()) {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(
                    "/com/example/ada7_base/Vista_GraficaPastel.fxml"));
            Parent nodoRaiz = cargadorFXML.load();

            this.controladorPastel = cargadorFXML.getController();
            this.controladorPastel.init(listaProductos);
            this.escenarioPastel.setScene(new Scene(nodoRaiz));
            this.escenarioPastel.setX(localizadorHorizontal - 700);
            this.escenarioPastel.setY(localizadorVertical);
        }
        this.escenarioPastel.show();
    }

    void mostrarBarras() throws IOException {
        registrador.info("Mostrando gráfica de barras");

        if (this.escenarioBarras == null || !this.escenarioBarras.isShowing()) {
            FXMLLoader cargadorFXML = new FXMLLoader(
                    getClass()
                            .getResource("/com/example/ada7_base/Vista_GraficaBarras.fxml"));
            Parent nodoRaiz = cargadorFXML.load();
            this.controladorBarras = cargadorFXML.getController();
            this.controladorBarras.init(listaProductos);
            this.escenarioBarras.setScene(new Scene(nodoRaiz));
            this.escenarioBarras.setX(localizadorHorizontal);
            this.escenarioBarras.setY(localizadorVertical);
        }
        this.escenarioBarras.show();
    }
    void actualizarNumeros(String productoVotado) {
        Producto producto = listaProductos.encontrarProducto(productoVotado);

        Label etiquetaConteo = etiquetasVotos.get(productoVotado);

        if (etiquetaConteo != null) {
            etiquetaConteo
                    .setText("Conteo de votos por " + producto.obtenerNombre() + ": " + producto.obtenerTotalVotos());
        }
        if (!(this.controladorBarras == null || this.controladorPastel == null)) {
            this.controladorBarras.actualizarBarras(productoVotado);
            this.controladorPastel.actualizarPastel(productoVotado);
        }

    }

    private ImageView crearImagenProducto(Producto producto) {
        Image imagen = new Image(getClass().getResourceAsStream(producto.obtenerImageUrl()));
        ImageView imagenVista = new ImageView(imagen);
        imagenVista.setFitWidth(50);
        imagenVista.setFitHeight(50);
        imagenVista.setPreserveRatio(true);
        return imagenVista;
    }

    private Label crearEtiquetaVotos(Producto producto) {
        Label conteo = new Label("Conteo de votos por " + producto.obtenerNombre() + ": " + producto.obtenerTotalVotos());
        etiquetasVotos.put(producto.obtenerNombre(), conteo);
        return conteo;
    }

    private Button crearBotonVoto(Producto producto) {
        Button boton = new Button("Vota por: " + producto.obtenerNombre());
        boton.setOnAction(evento -> {
            listaProductos.votarProducto(producto.obtenerNombre());
            actualizarNumeros(producto.obtenerNombre());
        });
        return boton;
    }

    private HBox crearContenedorDeBotonesDeGraficas(int spacing, Button btnPastel, Button btnBarras){
        HBox cajaHorizontalBoton = new HBox();
        cajaHorizontalBoton.setSpacing(spacing);
        cajaHorizontalBoton.setAlignment(Pos.CENTER);
        cajaHorizontalBoton.getChildren().addAll(btnPastel, btnBarras);
        return cajaHorizontalBoton;

    }
    private Button crearBotonGrafica(String nombreGrafica){
        Button botonGrafica = new Button(nombreGrafica);
        botonGrafica.setOnAction(evento -> {
            try {
                if (nombreGrafica.equals("Gráfica de pastel")){
                    mostrarPastel();
                }else{
                    mostrarBarras();
                }
            } catch (IOException exepcion) {
                throw new RuntimeException(exepcion);
            }
        });
        return botonGrafica;
    }









    /*
    private void mostrarEscenario(Stage escenario, String fxmlPath, Object controlador) throws IOException {
        if (escenario == null || !escenario.isShowing()) {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent nodoRaiz = cargadorFXML.load();
            controlador = cargadorFXML.getController();
            ((ControladorGrafico) controlador).init(listaProductos); // Asegúrate de que el controlador implemente una interfaz `ControladorGrafico`.
            escenario.setScene(new Scene(nodoRaiz));
            escenario.setX(this.localizadorHorizontal);
            escenario.setY(this.localizadorVertical);
        }
        escenario.show();
    }
    */
}
