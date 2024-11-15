package ada8.broker.infraestructura;

import ada8.broker.dominio.broker.*;
import ada8.comun.utilidades.Mensaje;
import ada8.comun.utilidades.MensajeTipo;
import ada8.comun.utilidades.Variable;

public class BrokerImpl extends Broker {

    @Override
    public Mensaje procesarMensaje(Mensaje mensaje) {

        if (mensaje.getServicio().equals("registrar")) {

            return registrarServicio(
                    mensaje.getVariableValor("servidor"),
                    Integer.parseInt(mensaje.getVariableValor("puerto")),
                    mensaje.getVariableValor("servicio"),
                    Integer.parseInt(mensaje.getVariableValor("parametros")));

        } else if (mensaje.getServicio().equals("listar")) {

            return listarServicios(mensaje.getVariableValor("palabra"));

        } else if (mensaje.getServicio().equals("ejecutar")) {
            Servicio servicio = buscarServicioRegistrado(mensaje.getVariableValor("servicio"));
            return servicio.ejecutar(mensaje);
        }

        return null;

    }

    private Mensaje registrarServicio(String ip, int puerto, String nombre, int numeroParametros) {
        Servicio servicio = new Servicio(ip, puerto, nombre, numeroParametros);
        servicio.setIdentificador(servicio.hashCode());
        addServicio(servicio);

        Mensaje mensaje = new Mensaje(MensajeTipo.RESPUESTA);
        mensaje.setServicio("registrar");
        mensaje.setNumeroVariables(1);
        mensaje.addVariable(new Variable("identificador", String.valueOf(servicio.getIdentificador())));

        return mensaje;
    }

    private Mensaje listarServicios(String palabra) {

        Mensaje mensaje = new Mensaje(MensajeTipo.RESPUESTA);
        mensaje.setServicio("listar");

        if (palabra == null) {
            for (Servicio servicio : getServicios()) {
                mensaje.addVariable(
                        new Variable(
                                servicio.getNombreServicio(),
                                servicio.getIpServidor() + ":" + servicio.getPuertoServidor()));

            }
        } else {
            for (Servicio servicio : getServicios()) {
                if (servicio.getNombreServicio().contains(palabra)) {
                    mensaje.addVariable(
                            new Variable(
                                    servicio.getNombreServicio(),
                                    servicio.getIpServidor() + ":" + servicio.getPuertoServidor()));
                }
            }
        }

        mensaje.setNumeroVariables(mensaje.getContenido().size());

        return mensaje;
    }

}
