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
        // Construtor padrão
    }

    public ProducaoLeiteSemanalDTO(String semana, Double producaoLeite) {
        this.semana = semana;
        this.producaoLeite = producaoLeite;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public Double getProducaoLeite() {
        return producaoLeite;
    }

    public void setProducaoLeite(Double producaoLeite) {
        this.producaoLeite = producaoLeite;
    }
}
