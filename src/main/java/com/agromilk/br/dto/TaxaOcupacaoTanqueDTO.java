package com.agromilk.br.dto;

public class TaxaOcupacaoTanqueDTO {
    private Long idTanque;
    private String modelo;
    private Double taxaOcupacao;

    // Construtores, getters e setters

    public TaxaOcupacaoTanqueDTO(Long idTanque, String modelo, Double taxaOcupacao ) {
        this.idTanque = idTanque;
        this.modelo = modelo;
        this.taxaOcupacao = taxaOcupacao;
    }

    public Long getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(Long idTanque) {
        this.idTanque = idTanque;
    }

    public Double getTaxaOcupacao() {
        return taxaOcupacao;
    }

    public void setTaxaOcupacao(Double taxaOcupacao) {
        this.taxaOcupacao = taxaOcupacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
