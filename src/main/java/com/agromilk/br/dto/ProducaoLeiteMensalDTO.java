package com.agromilk.br.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProducaoLeiteMensalDTO {

    @JsonProperty("mes")
    private String mes;

    @JsonProperty("producaoLeite")
    private Double producaoLeite;

    public ProducaoLeiteMensalDTO() {
        // Default constructor
    }

    public ProducaoLeiteMensalDTO(String mes, Double producaoLeite) {
        this.mes = mes;
        this.producaoLeite = producaoLeite;
    }

    // Getters and setters
}
