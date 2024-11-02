package com.ada8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import ada8.utils.mapper.Mensaje;
import ada8.utils.mapper.MensajeMapeador;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws IOException
     */
    @Test
    public void MensajeMapperTest() throws IOException {
        String content = Files.readString(Paths.get(
                "C:/Users/rodri/Desktop/software-architecture/tareas/ADA8/utils/src/test/java/com/ada8/jsonTest.json"));

        Mensaje mensaje = MensajeMapeador.deJsonAObjeto(content);

        String otroMensaje = MensajeMapeador.deObjetoAJson(mensaje);

        System.err.println(content);
        System.err.println("--------------------------------------------------");
        System.err.println(mensaje);
        System.err.println("--------------------------------------------------");
        System.err.println(otroMensaje);
    }
}
