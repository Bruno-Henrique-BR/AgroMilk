package com.agromilk.br.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "TIPO_OPERACAO")
@Data
public class TipoOperacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_OPERACAO")

    private Long idTipoOperacao;

    private String nome;

    private String descricao;

    private String tipoOperacao;

    private Boolean movimentacao;


}
