package com.agromilk.br.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ProducaoLeiteDiariaDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    @JsonProperty("dataDia")
    private String dataDia;

    @JsonProperty("quantidadeOrdenhas")
    private Long quantidadeOrdenhas;
    @JsonProperty("somaProducaoLeite")
    private Double somaProducaoLeite;



    public ProducaoLeiteDiariaDTO(String dataDia, Long quantidadeOrdenhas,  Double somaProducaoLeite ) {
        this.dataDia = dataDia;
        this.quantidadeOrdenhas = quantidadeOrdenhas;
        this.somaProducaoLeite = somaProducaoLeite;
    }

    public String getDataDia() {
        return dataDia;
    }
    public Long getQuantidadeOrdenhas() { return quantidadeOrdenhas;}
    public Double getSomaProducaoLeite() {
        return somaProducaoLeite;
    }


}
