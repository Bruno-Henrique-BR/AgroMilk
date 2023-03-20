package com.agromilk.br.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class LoteRequestDTO {

    private Long idLote;

    @NotEmpty(message = "Nome do lote é obrigatório")
    private String nome;
    private String descricao;

}