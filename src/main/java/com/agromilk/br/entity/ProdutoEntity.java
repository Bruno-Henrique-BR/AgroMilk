package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID_MARCA")
    private MarcaEntity idMarca;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataValidade;
    @OneToOne
    @JoinColumn(name = "ID_TIPO_PRODUTO", referencedColumnName = "ID_TIPO_PRODUTO")
    private TipoProdutoEntity idTipoProduto;
    private String descricao;
    private String codBarras;

}
