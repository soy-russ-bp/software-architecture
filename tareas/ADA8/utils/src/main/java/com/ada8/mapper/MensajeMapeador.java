package com.ada8.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MensajeMapeador {

    /// esta clase es de implementación, no de diseño, así que no se documenta en el
    /// diagrama de clases
    /// además, dado que la clase es privada, no sale de la clase MensajeMapeador,
    /// estos parametros son usados internamente
    private static class ParametrosMensaje {
        public MensajeTipo mensajeTipo;
        public final String campoServicio = "servicio";
        public String numeroVariablesORespuestas;
        public String variableUnicaORespuesta;
        public final String valorUnico = "valor";
    }

    private static ParametrosMensaje getParametrosMensaje(MensajeTipo mensajeTipo) {
        ParametrosMensaje parametrosMensaje = new ParametrosMensaje();

        parametrosMensaje.mensajeTipo = mensajeTipo;

        switch (mensajeTipo) {

            case MensajeTipo.PETICION:
                parametrosMensaje.numeroVariablesORespuestas = "variables";
                parametrosMensaje.variableUnicaORespuesta = "variable";
                break;

            case MensajeTipo.RESPUESTA:
                parametrosMensaje.numeroVariablesORespuestas = "respuestas";
                parametrosMensaje.variableUnicaORespuesta = "respuesta";
                break;

            default:
                break;
        }
        return parametrosMensaje;
    }

    public static Mensaje deJsonAObjeto(String json)
            throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapeador = new ObjectMapper();
        JsonNode nodoRaiz = mapeador.readTree(json);
        MensajeTipo mensajeTipo = null;

        if (nodoRaiz.get("respuestas") != null)
            mensajeTipo = MensajeTipo.RESPUESTA;
        if (nodoRaiz.get("variables") != null)
            mensajeTipo = MensajeTipo.PETICION;

        ParametrosMensaje parametrosMensaje = getParametrosMensaje(mensajeTipo);

        return convertirAObjeto(nodoRaiz, parametrosMensaje);
    }

    private static Mensaje convertirAObjeto(JsonNode nodoRaiz, ParametrosMensaje parametrosMensaje) {

        Mensaje mensaje = new Mensaje(parametrosMensaje.mensajeTipo);

        String servicio = nodoRaiz.get(parametrosMensaje.campoServicio).asText();
        mensaje.setServicio(servicio);

        int numeroVariables = nodoRaiz.get(parametrosMensaje.numeroVariablesORespuestas).asInt();
        mensaje.setNumeroVariables(numeroVariables);

        for (int i = 1; i < mensaje.getNumeroVariables() + 1; i++) {

            String nombreVariable = nodoRaiz.get(parametrosMensaje.variableUnicaORespuesta + i).asText();
            String valorVariable = nodoRaiz.get(parametrosMensaje.valorUnico + i).asText();

            Variable variable = new Variable(nombreVariable, valorVariable);
            mensaje.addVariable(variable);
        }

        return mensaje;
    }

    public static String deObjetoAJson(Mensaje mensaje) throws JsonProcessingException {

        ParametrosMensaje parametrosMensaje = getParametrosMensaje(mensaje.getMensajeTipo());

        ObjectMapper mapeador = new ObjectMapper();
        ObjectNode nodoRaiz = mapeador.createObjectNode();

        nodoRaiz.put(parametrosMensaje.campoServicio, mensaje.getServicio());
        nodoRaiz.put(parametrosMensaje.numeroVariablesORespuestas, mensaje.getNumeroVariables());

        for (int i = 0; i < mensaje.getNumeroVariables(); i++) {

            Variable variable = mensaje.getVariable(i);
            nodoRaiz.put(parametrosMensaje.variableUnicaORespuesta + (i + 1), variable.getNombre());
            nodoRaiz.put(parametrosMensaje.valorUnico + (i + 1), variable.getValor());
        }

        return mapeador.writeValueAsString(nodoRaiz);
    }

}