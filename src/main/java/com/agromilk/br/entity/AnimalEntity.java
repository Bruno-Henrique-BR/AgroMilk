package com.agromilk.br.entity;

import com.agromilk.br.util.LenientLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ANIMAL")
@Data
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANIMAL")
    private Long idAnimal;
    private String codigo;
    private String apelido;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataNascimento;

    @JsonDeserialize(using = LenientLocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataCompra;

    @ManyToOne
    @JoinColumn(name = "ID_LOTE",referencedColumnName = "ID_LOTE")
    private LoteEntity lote;

    @OneToOne
    @JoinColumn(name = "ID_RACA", referencedColumnName = "ID_RACA")
    private RacaEntity raca;

    @Formula("(SELECT AVG((ordenha.primeira_Ordenha + ordenha.segunda_Ordenha) / 2) FROM ORDENHA ordenha WHERE ordenha.id_animal = id_animal)")
    private Double media;




}
