package com.ada8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.ada8.mapper.Mensaje;
import com.ada8.mapper.MensajeMapeador;
import com.ada8.mapper.MensajeTipo;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws IOException 
     */
    @Test
    public void MensajeMapperTest() throws IOException
    {
          String content = Files.readString(Paths.get("C:/Users/rodri/Desktop/software-architecture/tareas/ADA8/utils/src/test/java/com/ada8/jsonTest.json"));

        Mensaje mensaje  = MensajeMapeador.deJsonAObjeto(content, MensajeTipo.PETICION);
        System.err.println(mensaje);
    }
}
