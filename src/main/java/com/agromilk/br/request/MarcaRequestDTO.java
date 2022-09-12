package com.agromilk.br.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class MarcaRequestDTO {

    private Long idMarca;

    @NotNull(message = "Nome da marca é obrigatório")
    private String nomeMarca;

}
