package com.agromilk.br.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ProducaoLeiteDiariaDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    @JsonProperty("dataDia")
    private String dataDia;
    @JsonProperty("somaProducaoLeite")
    private Double somaProducaoLeite;

    public ProducaoLeiteDiariaDTO(String dataDia, Double somaProducaoLeite) {
        this.dataDia = dataDia;
        this.somaProducaoLeite = somaProducaoLeite;
    }

    public String getDataDia() {
        return dataDia;
    }

    public Double getSomaProducaoLeite() {
        return somaProducaoLeite;
    }
}
