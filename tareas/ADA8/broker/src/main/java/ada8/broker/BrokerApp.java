package ada8.broker;

import ada8.broker.dominio.broker.Broker;
import ada8.broker.infraestructura.BrokerImpl;

public class BrokerApp {

    public static void main(String[] args) {

        Broker broker = new BrokerImpl();
        broker.setPuertoServidor(4040);
        broker.start();

    }
}
