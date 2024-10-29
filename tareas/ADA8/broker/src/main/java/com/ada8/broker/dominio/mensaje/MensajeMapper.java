package com.ada8.broker.dominio.mensaje;

public class MensajeMapper {
    public static Mensaje deJsonAObjecto(String json) {

        return new Mensaje();
    }

    public static String deObjetoAJson(Mensaje mensjae) {

        return "En el nombre del padre, del hijo y del espiritu santo";
    }

}
