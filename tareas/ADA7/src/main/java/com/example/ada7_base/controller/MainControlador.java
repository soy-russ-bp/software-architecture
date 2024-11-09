package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.ListaRegistros;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainControlador {

    private static final double localizadorHorizontal = 400;
    private static final double localizadorVertical = 512;

    @FXML
    private VBox cajaVerticalProductos;
    private Map<String, Stage> escenariosVistas = new HashMap<>();
    private Map<String, Label> etiquetasVotos = new HashMap<>();
    private static ListaProductos listaProductos = new ListaProductos();

    private Observable controladoresVistas = new Observable();

    @FXML
    public void initialize() throws IOException {
        listaProductos.contarProductos();
        for (Producto producto : listaProductos.obtenerProductos()) {
            ImageView imagenVista = this.crearImagenProducto(producto);
            cajaVerticalProductos.getChildren().add(imagenVista);

            Label conteo = this.crearEtiquetaVotos(producto);
            cajaVerticalProductos.getChildren().add(conteo);

            Button boton = this.crearBotonVoto(producto);
            cajaVerticalProductos.getChildren().add(boton);
        }
        this.escenariosVistas.put("Pastel", new Stage());
        this.escenariosVistas.put("Barras", new Stage());

        Button btnPastel = this.crearBotonAbrirVista("Gráfica de pastel");
        Button btnBarras = this.crearBotonAbrirVista("Gráfica de barras");
        Button btnListar = this.crearBotonAbrirVista("Listar productos");

        HBox cajaHorizontalBoton = this.crearContenedorDeBotonesDeVistas(10, btnPastel, btnBarras, btnListar);
        cajaVerticalProductos.getChildren().add(cajaHorizontalBoton);
        cajaVerticalProductos.setAlignment(Pos.TOP_CENTER);

    }
    void mostrarPastel() throws IOException {
        Stage escenarioPastel = this.escenariosVistas.get("Pastel");
        escenarioPastel  = this.mostrarEscenarioVistas("/com/example/ada7_base/Vista_GraficaPastel.fxml", escenarioPastel);
        this.escenariosVistas.get("Pastel").show();
    }

    void mostrarBarras() throws IOException {
        Stage escenarioBarras = this.escenariosVistas.get("Barras");
        escenarioBarras = this.escenariosVistas.get("Barras");
        escenarioBarras  = this.mostrarEscenarioVistas("/com/example/ada7_base/Vista_GraficaBarras.fxml", escenarioBarras);
        this.escenariosVistas.get("Barras").show();
    }

    void mostrarRegistros() throws IOException {
        Stage escenarioRegistros = this.escenariosVistas.get("Registros");
        escenarioRegistros = this.escenariosVistas.get("Registros");
        escenarioRegistros  = this.mostrarEscenarioVistas("/com/example/ada7_base/Vista_ListarRegistros.fxml", escenarioRegistros);
        this.escenariosVistas.get("Registros").show();
    }

    void actualizarNumeros(String productoVotado) {
        Producto producto = listaProductos.encontrarProducto(productoVotado);
        Label etiquetaConteo = etiquetasVotos.get(productoVotado);
        if (etiquetaConteo != null) {
            etiquetaConteo
                    .setText("Conteo de votos por " + producto.obtenerNombre() + ": " + producto.obtenerTotalVotos());
        }
        this.controladoresVistas.notificarObservadores(productoVotado);
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
                ListaRegistros.registrarEvento(producto.obtenerNombre());
            } catch (IOException e) {
                e.printStackTrace();
            }
            actualizarNumeros(producto.obtenerNombre());
        });
        return boton;
    }

    private HBox crearContenedorDeBotonesDeVistas(int spacing, Button btnPastel, Button btnBarras, Button btnListar) {
        HBox cajaHorizontalBoton = new HBox();
        cajaHorizontalBoton.setSpacing(spacing);
        cajaHorizontalBoton.setAlignment(Pos.CENTER);
        cajaHorizontalBoton.getChildren().addAll(btnPastel, btnBarras, btnListar);
        return cajaHorizontalBoton;

    }
    private Button crearBotonAbrirVista(String nombreVista){
        Button botonGrafica = new Button(nombreVista);
        botonGrafica.setOnAction(evento -> {
            try {
                if (nombreVista.equals("Gráfica de pastel")){
                    mostrarPastel();
                }else if(nombreVista.equals("Gráfica de barras")){
                    mostrarBarras();
                }else{
                    mostrarRegistros();
                }
            } catch (IOException exepcion) {
                throw new RuntimeException(exepcion);
            }
        });
        return botonGrafica;
    }

    private Stage mostrarEscenarioVistas(String fxmlRuta, Stage escenarioVista) throws IOException {
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(fxmlRuta));
        Parent nodoRaiz = cargadorFXML.load();
        var controlador = (IObservador)cargadorFXML.getController();
        this.controladoresVistas.agregarObservador(controlador);
        escenarioVista.setScene(new Scene(nodoRaiz));
        escenarioVista.setX(localizadorHorizontal);
        escenarioVista.setY(localizadorVertical);
        return escenarioVista;
    }

}
