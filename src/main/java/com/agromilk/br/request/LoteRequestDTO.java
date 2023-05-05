package com.agromilk.br.request;

import com.agromilk.br.entity.TipoLoteEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class LoteRequestDTO {

    private Long idLote;

    @NotEmpty(message = "Nome do lote é obrigatório")
    private String nomeLote;
    private String descricao;
    @NotNull(message = "Tipo de lote é obrigatório")
    private TipoLoteEnum tipoLote;

}