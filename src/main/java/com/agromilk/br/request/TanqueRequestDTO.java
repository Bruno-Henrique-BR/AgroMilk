package com.agromilk.br.request;

import com.agromilk.br.entity.MarcaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TanqueRequestDTO {

    private Long idTanque;

    private String descricao;

    @NotNull(message = "Capacidade é obrigatório")
    private Double capacidade;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA",referencedColumnName = "ID_MARCA")
    @NotNull(message = "Marca é obrigatório")
    private MarcaEntity marca;

    @NotNull(message = "Modelo é obrigatório")
    private String modelo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Data de fabricação é obrigatório")
    private LocalDate dataFabricacao;

    private Boolean ativo;

}
