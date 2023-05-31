package com.agromilk.br.dto;

import com.agromilk.br.entity.RacaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RacaDTO {

    private  Long idRaca;
    private  String nomeRaca;
    private  String descricao;

    public RacaDTO() {
        super();
    }

    public RacaDTO(RacaEntity obj) {
        super();
        this.idRaca = obj.getIdRaca();
        this.nomeRaca = obj.getNomeRaca();
        this.descricao = obj.getDescricao();
    }
}
