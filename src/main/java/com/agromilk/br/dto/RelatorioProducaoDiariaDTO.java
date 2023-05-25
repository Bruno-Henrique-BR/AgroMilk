package com.agromilk.br.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RelatorioProducaoDiariaDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private Date dataInicial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private Date dataFinal;
    private List<ProducaoLeiteDiariaDTO> producaoDiaria;
    private double somaProducao;

    public RelatorioProducaoDiariaDTO(Date dataInicial, Date dataFinal, List<ProducaoLeiteDiariaDTO> producaoDiaria, double somaProducao) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.producaoDiaria = producaoDiaria;
        this.somaProducao = somaProducao;
    }

    // Getters e Setters

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<ProducaoLeiteDiariaDTO> getProducaoDiaria() {
        return producaoDiaria;
    }

    public void setProducaoDiaria(List<ProducaoLeiteDiariaDTO> producaoDiaria) {
        this.producaoDiaria = producaoDiaria;
    }

    public double getSomaProducao() {
        return somaProducao;
    }

    public void setSomaProducao(double somaProducao) {
        this.somaProducao = somaProducao;
    }
}
