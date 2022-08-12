package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "REBANHO")
@Data
public class RebanhoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REBANHO")
    private Long idRebanho;

    private String nome;

    private String descricao;
}
