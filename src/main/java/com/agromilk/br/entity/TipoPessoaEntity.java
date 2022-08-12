package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_PESSOA")
@Data
public class TipoPessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PESSOA")
    private Long idTipoPessoa;

    private String tipo;
}
