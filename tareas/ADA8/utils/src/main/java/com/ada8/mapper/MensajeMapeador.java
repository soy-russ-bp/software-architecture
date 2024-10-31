package com.ada8.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MensajeMapeador {

    // solo el nombre del campo dentro del json, no los valores de los campos en sí
    private static String campoServicio = "servicio";
    private static String campoVariables = "variables";

    private static String variableUnica = "variable";
    private static String valorUnico = "valor";

    public static Mensaje deJsonAObjeto(String json, MensajeTipo mensajeTipo)
            throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapeador = new ObjectMapper();
        JsonNode nodoRaiz = mapeador.readTree(json);

        Mensaje mensaje = new Mensaje();

        switch (mensajeTipo) {
            case MensajeTipo.PETICION:

                String servicio = nodoRaiz.get(campoServicio).asText();
                mensaje.setServicio(servicio);

                String numeroVariables = nodoRaiz.get(campoVariables).asText();
                mensaje.setNumeroVariables(Integer.parseInt(numeroVariables));

                for (int i = 1; i < mensaje.getNumeroVariables() + 1; i++) {

                    String nombreVariable = nodoRaiz.get(variableUnica + i).asText();
                    String valorVariable = nodoRaiz.get(valorUnico + i).asText();

                    Variable variable = new Variable(nombreVariable, valorVariable);
                    mensaje.addVariable(variable);
                }

                break;
            case MensajeTipo.RESPUESTA:

                break;

            default:
                break;
        }

        return mensaje;
    }

}