package ada8.servidor.infraestructura;

import java.net.UnknownHostException;
import java.util.ArrayList;

import ada8.comun.cliente.Cliente;
import ada8.servidor.dominio.Parametros;
import ada8.servidor.dominio.Servicio;
import ada8.servidor.dominio.ServidorRemoto;
import ada8.servidor.infraestructura.parametros.*;
import ada8.servidor.infraestructura.servicios.*;
import ada8.comun.utilidades.Mensaje;
import ada8.comun.utilidades.MensajeTipo;
import ada8.comun.utilidades.Variable;

public class ServidorImpl extends ServidorRemoto {

    public ServidorImpl() {
        setServicios(
                new ArrayList<ada8.servidor.dominio.Servicio>() {
                    {
                        add(new ServicioContarVotos());
                        add(new ServicioListarEventos());
                        add(new ServicioRegistrarEvento());
                        add(new ServicioVotar());
                    }
                });

    }

    @Override
    public void start() {
        registrarServiciosAlBroker();
        super.start();
    }

    @Override
    public void registrarServiciosAlBroker() {
        Cliente cliente = new Cliente(getIpBroker(), getPuertoBroker());

        for (Servicio servicio : getServicios()) {
            Mensaje mensaje = new Mensaje(MensajeTipo.PETICION);

            mensaje.setServicio("registrar");
            mensaje.setNumeroVariables(4);
            ArrayList<Variable> contenido;
            try {
                contenido = new ArrayList<Variable>() {
                    {
                        add(new Variable("servidor", java.net.InetAddress.getLocalHost().getHostAddress()));

                        add(new Variable("puerto", String.valueOf(getPuertoServidor())));
                        add(new Variable("servicio", servicio.getNombre()));
                        add(new Variable("parametros", String.valueOf(9999)));

                    }
                };
                mensaje.setContenido(contenido);
                cliente.enviarMensaje(mensaje);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Mensaje procesarMensaje(Mensaje mensaje) {

        Servicio servicio = buscarServicioPorNombre(mensaje.getServicio());
        Parametros parametros = getParametros(mensaje);
        Mensaje respuestaMensaje = servicio.ejecutar(parametros);

        return respuestaMensaje;
    }

    private Parametros getParametros(Mensaje mensaje) {

        if (mensaje.getServicio().equals("votar")) {
            String nombreProducto = mensaje.getVariable(0).getNombre();
            int numeroVotos = Integer.parseInt(mensaje.getVariable(0).getValor());
            return new ParametrosVotar(nombreProducto, numeroVotos);

        } else if (mensaje.getServicio().equals("registrar")) {
            String nombreEvento = mensaje.getVariable(0).getValor();
            String fechaHora = mensaje.getVariable(1).getValor();
            return new ParametrosRegistrarEvento(nombreEvento, fechaHora);
        } else {
            // para el caso de listar y contar que no requieren parametros
            return null;
        }
    }

}
