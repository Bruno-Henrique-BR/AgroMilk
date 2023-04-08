package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.ColetaEntity;
import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class ColetaDTO {

    private Long idColeta;

    private TanqueEntity tanque;

    private LaticinioEntity laticinio;

    private Double quantidade;

    private String descricao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate data;

    public ColetaDTO() {
        super();
    }

    public ColetaDTO(ColetaEntity obj) {
        super();
        this.idColeta = obj.getIdColeta();
        this.tanque = obj.getTanque();
        this.laticinio = obj.getLaticinio();
        this.quantidade = obj.getQuantidade();
        this.descricao = obj.getDescricao();
        this.data = obj.getData();


    }
}
