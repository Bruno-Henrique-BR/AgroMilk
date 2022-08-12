package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "ANALISE_LEITE")
@Data
public class AnaliseLeiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANALISE_LEITE")
    private Long idAnaliseLeite;
    @OneToOne
    @JoinColumn(name = "fk_coleta", nullable = false)
    private ColetaEntity idColeta;
    private Date data;
    private Double gordura;
    private Double proteina;
    private Double lactose;
    private Double ph;
    private Double contBacteria;
    private Double valor;
}
