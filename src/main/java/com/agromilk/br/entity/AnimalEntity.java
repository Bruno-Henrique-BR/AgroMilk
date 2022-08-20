package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;
    private String cor;
    @ManyToOne
    @JoinColumn(name = "ID_REBANHO",referencedColumnName = "ID_REBANHO")
    private RebanhoEntity rebanho;

    @OneToOne
    @JoinColumn(name = "ID_RACA", referencedColumnName = "ID_RACA")
    private RacaEntity raca;

    private String sexo;


}
