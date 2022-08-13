package com.agromilk.br.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ANIMAL")
@Data
@RequiredArgsConstructor
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANIMAL")
    private Long idAnimal;
    private String codigo;
    private String apelido;
    private LocalDate dataNascimento;
    private LocalDate dataCompra;
    private String cor;
    @ManyToOne
    @JoinColumn(name = "ID_REBANHO",referencedColumnName = "ID_REBANHO")
    private RebanhoEntity rebanho;


}
