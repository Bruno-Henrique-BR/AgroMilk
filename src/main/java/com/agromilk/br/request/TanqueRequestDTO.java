package com.agromilk.br.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TanqueRequestDTO {

    private Long idTanque;

    private String descricao;

    @NotNull(message = "Capacidade é obrigatório")
    private Double capacidade;


    @NotNull(message = "Modelo é obrigatório")
    private String modelo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm/DD/yyyy")
    private LocalDate dataFabricacao;

    private Double quantidadeAtual = 0.0;

}
