package com.agromilk.br.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RacaRequestDTO {

    private Long idRaca;
    @NotNull(message = "Nome da raça é obrigatório")
    private String nome;
    @NotNull(message = "Descricao da raça é obrigatório")
    private String descricao;

}
