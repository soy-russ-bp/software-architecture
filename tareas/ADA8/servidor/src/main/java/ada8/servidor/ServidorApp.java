package ada8.servidor;

import ada8.servidor.dominio.ServidorRemoto;
import ada8.servidor.infraestructura.ServidorImpl;

/**
 * Hello world!
 *
 */
public class ServidorApp {

    public static void main(String[] args) {

        ServidorRemoto servidor = new ServidorImpl();
        servidor.setPuertoServidor(3030);
        servidor.setPuertoBroker(4040);
        servidor.setIpBroker("192.168.1.5");
 

        servidor.start();
       
    }
}
