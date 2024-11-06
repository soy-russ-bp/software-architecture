package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import com.example.ada7_base.observer.IObservador;
import com.example.ada7_base.observer.Observable;
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
    private static final double localizadorVertical = 512;

    @FXML
    private VBox cajaVerticalProductos;
    private Map<String, Stage> escenariosGraficas = new HashMap<>();
    private Map<String, Label> etiquetasVotos = new HashMap<>();
    private static ListaProductos listaProductos = new ListaProductos();

    private Observable controladoresDeGraficas = new Observable();

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
        this.escenariosGraficas.put("Pastel", new Stage());
        this.escenariosGraficas.put("Barras", new Stage());

        Button btnPastel = this.crearBotonGrafica("Gráfica de pastel");
        Button btnBarras = this.crearBotonGrafica("Gráfica de barras");

        HBox cajaHorizontalBoton = this.crearContenedorDeBotonesDeGraficas(10, btnPastel, btnBarras);
        cajaVerticalProductos.getChildren().add(cajaHorizontalBoton);
        cajaVerticalProductos.setAlignment(Pos.TOP_CENTER);

        registrador.info("Vista principal inicializada");
    }
    void mostrarPastel() throws IOException {
        registrador.info("Mostrando gráfica de pastel");
        Stage escenarioPastel = this.escenariosGraficas.get("Pastel");
        escenarioPastel  = this.mostrarEscenarioGrafica("/com/example/ada7_base/Vista_GraficaPastel.fxml", escenarioPastel);
        this.escenariosGraficas.get("Pastel").show();
    }

    void mostrarBarras() throws IOException {
        registrador.info("Mostrando gráfica de barras");
        Stage escenarioBarras = this.escenariosGraficas.get("Barras");
        escenarioBarras = this.escenariosGraficas.get("Barras");
        escenarioBarras  = this.mostrarEscenarioGrafica("/com/example/ada7_base/Vista_GraficaBarras.fxml", escenarioBarras);
        this.escenariosGraficas.get("Barras").show();
    }

    void actualizarNumeros(String productoVotado) {
        Producto producto = listaProductos.encontrarProducto(productoVotado);
        Label etiquetaConteo = etiquetasVotos.get(productoVotado);
        if (etiquetaConteo != null) {
            etiquetaConteo
                    .setText("Conteo de votos por " + producto.obtenerNombre() + ": " + producto.obtenerTotalVotos());
        }
        this.controladoresDeGraficas.notificarObservadores(productoVotado);
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
            try {
                listaProductos.votarProducto(producto.obtenerNombre());
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private Stage mostrarEscenarioGrafica(String fxmlRuta, Stage escenarioGrafica) throws IOException {
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(fxmlRuta));
        Parent nodoRaiz = cargadorFXML.load();
        var controlador = (IObservador)cargadorFXML.getController();
        this.controladoresDeGraficas.agregarObservador(controlador, listaProductos);
        escenarioGrafica.setScene(new Scene(nodoRaiz));
        escenarioGrafica.setX(localizadorHorizontal);
        escenarioGrafica.setY(localizadorVertical);
        return escenarioGrafica;
    }

}