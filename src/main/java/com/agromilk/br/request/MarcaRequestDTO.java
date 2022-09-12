package com.agromilk.br.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MarcaRequestDTO {

    private Long idMarca;

    @NotEmpty(message = "Nome da marca é obrigatório")
    private String nomeMarca;

}
