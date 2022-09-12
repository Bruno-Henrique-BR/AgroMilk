package com.agromilk.br.dto;

import com.agromilk.br.entity.MarcaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
public class TanqueDTO {

    private Long idTanque;

    private String descricao;

    private Double capacidade;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA",referencedColumnName = "ID_MARCA")
    private MarcaEntity marca;

    private String modelo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFabricacao;

    private Boolean ativo;


}
