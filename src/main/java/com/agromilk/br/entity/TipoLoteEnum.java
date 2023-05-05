package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoLoteEnum {
    GESTANTES("Gestantes"),
    LACTANTES("Lactantes"),
    SECAS("Secas"),
    NOVILHAS("Novilhas"),
    BEZERRAS("Bezerras"),
    VENDIDAS("Vendidas"),
    FALECIDOS("Falecidos"),
    OUTRO_TIPO("Outro Tipo");

    private final String value;

    TipoLoteEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoLoteEnum fromValue(String value) {
        for (TipoLoteEnum tipo : TipoLoteEnum.values()) {
            if (tipo.value.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid TipoLoteEnum value: " + value);
    }
}
