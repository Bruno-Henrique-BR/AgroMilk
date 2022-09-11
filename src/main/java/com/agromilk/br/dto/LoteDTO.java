package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class LoteDTO {
    private Long idLote;
    private String nome;
    private String descricao;
    private List<AnimalEntity> list = new ArrayList<>();
    private String sexo;
}
