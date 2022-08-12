package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_PRODUTO")
@Data
public class TipoProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PRODUTO")
    private Long idTipoProduto;
    private String descricao;
}
