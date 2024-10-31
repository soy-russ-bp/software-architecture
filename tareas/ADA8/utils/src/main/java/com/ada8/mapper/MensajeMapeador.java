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
        public final String CAMPO_SERVICIO = "servicio";
        public String numeroVariablesORespuestas;
        public String variableUnicaORespuesta;
        public final String VALOR_UNICO = "valor";

        public ParametrosMensaje(MensajeTipo mensajeTipo) {
            this.mensajeTipo = mensajeTipo;

            switch (mensajeTipo) {

                case MensajeTipo.PETICION:
                    numeroVariablesORespuestas = "variables";
                    variableUnicaORespuesta = "variable";
                    break;

                case MensajeTipo.RESPUESTA:
                    numeroVariablesORespuestas = "respuestas";
                    variableUnicaORespuesta = "respuesta";
                    break;

                default:
                    break;
            }
        }
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

        ParametrosMensaje parametrosMensaje = new ParametrosMensaje(mensajeTipo);

        return convertirAObjeto(nodoRaiz, parametrosMensaje);
    }

    private static Mensaje convertirAObjeto(JsonNode nodoRaiz, ParametrosMensaje parametrosMensaje) {

        Mensaje mensaje = new Mensaje(parametrosMensaje.mensajeTipo);

        String servicio = nodoRaiz.get(parametrosMensaje.CAMPO_SERVICIO).asText();
        mensaje.setServicio(servicio);

        int numeroVariables = nodoRaiz.get(parametrosMensaje.numeroVariablesORespuestas).asInt();
        mensaje.setNumeroVariables(numeroVariables);

        for (int i = 1; i < mensaje.getNumeroVariables() + 1; i++) {

            String nombreVariable = nodoRaiz.get(parametrosMensaje.variableUnicaORespuesta + i).asText();
            String valorVariable = nodoRaiz.get(parametrosMensaje.VALOR_UNICO + i).asText();

            Variable variable = new Variable(nombreVariable, valorVariable);
            mensaje.addVariable(variable);
        }

        return mensaje;
    }

    public static String deObjetoAJson(Mensaje mensaje) throws JsonProcessingException {

        ParametrosMensaje parametrosMensaje = new ParametrosMensaje(mensaje.getMensajeTipo());

        ObjectMapper mapeador = new ObjectMapper();
        ObjectNode nodoRaiz = mapeador.createObjectNode();

        nodoRaiz.put(parametrosMensaje.CAMPO_SERVICIO, mensaje.getServicio());
        nodoRaiz.put(parametrosMensaje.numeroVariablesORespuestas, mensaje.getNumeroVariables());

        for (int i = 0; i < mensaje.getNumeroVariables(); i++) {

            Variable variable = mensaje.getVariable(i);
            nodoRaiz.put(parametrosMensaje.variableUnicaORespuesta + (i + 1), variable.getNombre());
            nodoRaiz.put(parametrosMensaje.VALOR_UNICO + (i + 1), variable.getValor());
        }

        return mapeador.writeValueAsString(nodoRaiz);
    }

}