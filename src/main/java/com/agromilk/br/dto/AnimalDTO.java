package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.util.LenientLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class AnimalDTO {
    private Long idAnimal;

    private String codigo;

    private String apelido;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataNascimento;

    @JsonDeserialize(using = LenientLocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataCompra;


    private String cor;

    private Long lote;

    private Long raca;

    private Boolean lactacao;

    public AnimalDTO() {
        super();
    }

    public AnimalDTO(AnimalEntity obj) {
        super();
        this.idAnimal = obj.getIdAnimal();
        this.codigo = obj.getCodigo();
        this.apelido = obj.getApelido();
        this.dataNascimento = obj.getDataNascimento();
        this.dataCompra = obj.getDataCompra();
        this.cor = obj.getCor();
        this.lote = obj.getLote().getIdLote();
        this.raca = obj.getRaca().getIdRaca();
        this.lactacao = obj.getLactacao();

    }


}
