package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "RACA")
@Data
public class RacaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RACA")
    Long idRaca;
    String nomeRaca;
    String descricao;
}
