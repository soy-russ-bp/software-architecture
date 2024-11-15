package ada8.broker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import ada8.broker.dominio.broker.Broker;
import ada8.broker.infraestructura.BrokerImpl;
import ada8.comun.cliente.Cliente;
import ada8.comun.utilidades.Mensaje;
import ada8.comun.utilidades.MensajeMapeador;
import ada8.servidor.dominio.ServidorRemoto;
import ada8.servidor.infraestructura.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws IOException 
          * @throws InterruptedException 
          */
         @Test
         public void BrokerTest() throws IOException, InterruptedException
    {
        Broker broker = new BrokerImpl();
        broker.setPuertoServidor(4545);
        broker.start();

        ServidorRemoto servidor = new ServidorImpl();
        servidor.setPuertoServidor(6969);
        servidor.setIpBroker( "192.168.1.2");
        servidor.setPuertoBroker(4545);
        servidor.start();

        Cliente cliente = new Cliente("192.168.1.2", 4545);
        String json = Files.readString(Paths.get("C:\\Users\\rodri\\Desktop\\software-architecture\\tareas\\ADA8\\broker\\src\\test\\java\\ada8\\broker\\test.json"));
        Mensaje mensaje = MensajeMapeador.deJsonAObjeto(json);
        Mensaje respuesta = cliente.enviarMensaje(mensaje);
        System.out.println(MensajeMapeador.deObjetoAJson(respuesta));
    }
}