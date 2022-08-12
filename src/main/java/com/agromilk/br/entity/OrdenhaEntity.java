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
    @JoinColumn(name = "fk_animal", nullable = false)
    private AnimalEntity idAnimal;

    private Date data;

    private Time hora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tanque", nullable = false)
    private TanqueEntity idTanque;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pessoa", nullable = false)
    private PessoaEntity idPessoa;

    private Double kg;




}
