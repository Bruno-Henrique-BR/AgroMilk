package com.agromilk.br.dto;

import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class TanqueDTO {

    private Long idTanque;

    private String descricao;

    private Double capacidade;

    private String modelo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFabricacao;


    private Double quantidadeAtual = 0.0;

    public TanqueDTO() {
        super();
    }

    public TanqueDTO(TanqueEntity obj) {
        super();
        this.idTanque = obj.getIdTanque();
        this.descricao = obj.getDescricao();
        this.capacidade = obj.getCapacidade();
        this.modelo = obj.getModelo();
        this.dataFabricacao = obj.getDataFabricacao();
        this.quantidadeAtual = obj.getQuantidadeAtual();


    }

}
