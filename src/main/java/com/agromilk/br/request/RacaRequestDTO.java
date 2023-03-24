package com.agromilk.br.request;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RacaRequestDTO {

    private Long idRaca;
    @NotEmpty(message = "Nome da raça é obrigatório")
    private String nomeRaca;
    @NotEmpty(message = "Descricao da raça é obrigatório")
    private String descricao;

}
