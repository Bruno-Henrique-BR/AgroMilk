package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "PRODUTO")
@Data
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long idProduto;
    private String codigo;
    private String nome;
    @OneToOne
    @JoinColumn(name = "fk_marca", nullable = false)
    private MarcaEntity idMarca;
    private LocalDate dataValidade;
    @OneToOne
    @JoinColumn(name = "fk_tipo_produto", nullable = false)
    private TipoProdutoEntity idTipoProduto;
    private String descricao;
    private String codBarras;

}
