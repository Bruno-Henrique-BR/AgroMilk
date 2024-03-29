package com.agromilk.br.entity;

//import com.agromilk.br.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ORDENHA")
@Data
@RequiredArgsConstructor
public class OrdenhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDENHA")
    private Long idOrdenha;

    @NotNull(message = "Data de ordenha é obrigatorio")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate data;

    @Column(name = "primeira_Ordenha")
    private Double primeiraOrdenha;

    private Double segundaOrdenha;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ANIMAL", referencedColumnName = "ID_ANIMAL")
    private AnimalEntity animal;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TANQUE", referencedColumnName = "ID_TANQUE")
    private TanqueEntity tanque;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO",referencedColumnName = "ID_FUNCIONARIO")
    private FuncionarioEntity funcionario;

}
