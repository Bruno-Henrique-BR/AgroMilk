package com.agromilk.br.entity;

//import com.agromilk.br.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull(message = "Quantidade é obrigatorio")
    private Double quantidade;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ANIMAL", referencedColumnName = "ID_ANIMAL")
    private AnimalEntity animal;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TANQUE", referencedColumnName = "ID_TANQUE")
    private TanqueEntity tanque;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO",referencedColumnName = "ID_USUARIO")
    private UsuarioEntity usuario;
    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO",referencedColumnName = "ID_FUNCIONARIO")
    private FuncionarioEntity funcionario;

}
