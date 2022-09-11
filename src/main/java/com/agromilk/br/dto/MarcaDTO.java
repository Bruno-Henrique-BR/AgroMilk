package com.agromilk.br.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MarcaDTO {
    private Long idMarca;
    private String descricao;
}
