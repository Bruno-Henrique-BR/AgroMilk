package com.agromilk.br.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProducaoLeiteSemanalDTO {

    @JsonProperty("semana")
    private String semana;

    @JsonProperty("producaoLeite")
    private Double producaoLeite;

    public ProducaoLeiteSemanalDTO() {
        // Default constructor
    }

    public ProducaoLeiteSemanalDTO(String semana, Double producaoLeite) {
        this.semana = semana;
        this.producaoLeite = producaoLeite;
    }

    // Getters and setters
}
