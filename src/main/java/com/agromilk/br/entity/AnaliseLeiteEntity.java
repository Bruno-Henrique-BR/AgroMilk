package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JoinColumn(name = "ID_COLETA", referencedColumnName = "ID_COLETA")
    private ColetaEntity idColeta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date data;
    private Double gordura;
    private Double proteina;
    private Double lactose;
    private Double ph;
    private Double contBacteria;
    private Double valor;
}
