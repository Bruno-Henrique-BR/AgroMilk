package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.RacaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
@Data
@EqualsAndHashCode()
public class LoteDTO {
    private Long idLote;
    private String nome;
    private String descricao;
    private List<AnimalEntity> list = new ArrayList<>();

    public LoteDTO() {
        super();
    }

    public LoteDTO(LoteEntity obj) {
        super();
        this.idLote = obj.getIdLote();
        this.nome = obj.getNomeLote();
        this.descricao = obj.getDescricao();
        this.list = obj.getList();
    }

}
