package com.agromilk.br.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RelatorioProducaoDiariaDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataInicial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataFinal;
    private List<ProducaoLeiteDiariaDTO> producaoDiaria;
    private double somaProducao;

    public RelatorioProducaoDiariaDTO(LocalDate dataInicial, LocalDate dataFinal, List<ProducaoLeiteDiariaDTO> producaoDiaria, double somaProducao) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.producaoDiaria = producaoDiaria;
        this.somaProducao = somaProducao;
    }

    // Getters e Setters

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
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
