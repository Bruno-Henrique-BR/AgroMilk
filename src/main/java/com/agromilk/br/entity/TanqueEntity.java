package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TANQUE")
@Data
public class TanqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TANQUE")
    private Long idTanque;

    private String descricao;

    private Double capacidade;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA",referencedColumnName = "ID_MARCA")
    private MarcaEntity marca;

    private String modelo;

    private Date dataFabricacao;

    private Boolean ativo;


}
