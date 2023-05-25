package com.agromilk.br.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProducaoLeiteDiariaRelatorioDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private Date data;
    private Double quantidadeProducao;


    public ProducaoLeiteDiariaRelatorioDTO(Date data, Double quantidadeProducao) {
        this.quantidadeProducao = quantidadeProducao;
        this.data = data;

    }


    public Date getData() {
        return data;
    }

    public Double getQuantidadeProducao() {
        return quantidadeProducao;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setQuantidadeProducao(Double quantidadeProducao) {
        this.quantidadeProducao = quantidadeProducao;
    }
}

