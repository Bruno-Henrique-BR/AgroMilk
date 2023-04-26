package com.agromilk.br.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "movimento")
public class MovimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private AnimalEntity animal;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private LoteEntity lote;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    public MovimentoEntity() {
    }

    public MovimentoEntity(AnimalEntity animal, LoteEntity lote, LocalDate dataEntrada) {
        this.animal = animal;
        this.lote = lote;
        this.dataEntrada = dataEntrada;
    }

    // getters e setters
}

