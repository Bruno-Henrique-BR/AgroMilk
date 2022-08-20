package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "ORDENHA")
@Data
@RequiredArgsConstructor
public class OrdenhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDENHA")
    private Long idOrdenha;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Data de ordenha é obrigatorio")
    private LocalDate data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time hora;

    private Double quantidade;
    @OneToOne
    @JoinColumn(name = "ID_ANIMAL", referencedColumnName = "ID_ANIMAL")
    private AnimalEntity animal;
    @OneToOne
    @JoinColumn(name = "ID_TANQUE", referencedColumnName = "ID_TANQUE")
    private TanqueEntity tanque;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO",referencedColumnName = "ID_USUARIO")
    private UsuarioEntity usuario;
    @ManyToOne
    @JoinColumn(name = "ID_PESSOA",referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;







}
