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

    private AnimalEntity idAnimal;

    private Date data;

    private Time hora;

    private TanqueEntity idTanque;

    private PessoaEntity idPessoa;

    private Double kg;




}
