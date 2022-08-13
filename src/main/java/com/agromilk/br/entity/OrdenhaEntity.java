package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "ORDENHA")
@Data
public class OrdenhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDENHA")
    private Long idOrdenha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ANIMAL", nullable = false)
    private AnimalEntity idAnimal;

    private Date data;

    private Time hora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TANQUE", nullable = false)
    private TanqueEntity idTanque;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ORDENHA", nullable = false)
    private PessoaEntity idPessoa;

    private Double quantidade;




}
