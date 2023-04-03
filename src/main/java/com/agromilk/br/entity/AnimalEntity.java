package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;

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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataCompra;
    private String cor;
    @ManyToOne
    @JoinColumn(name = "ID_LOTE",referencedColumnName = "ID_LOTE")
    private LoteEntity lote;

    @OneToOne
    @JoinColumn(name = "ID_RACA", referencedColumnName = "ID_RACA")
    private RacaEntity raca;

    private Boolean lactacao;



}
