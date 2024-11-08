package com.ada8;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeMapeador;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws IOException
     * @throws URISyntaxException 
     */
    @Test
    public void MensajeMapperTest() throws IOException, URISyntaxException {

        var filePath = MensajeMapeador.class.getResource("/jsonTest.json").toURI();
        
        String content = Files.readString(Path.of(filePath));

        Mensaje mensaje = MensajeMapeador.deJsonAObjeto(content);

        String otroMensaje = MensajeMapeador.deObjetoAJson(mensaje);

        System.err.println(content);
        System.err.println("--------------------------------------------------");
        System.err.println(mensaje);
        System.err.println("--------------------------------------------------");
        System.err.println(otroMensaje);
    }
}
