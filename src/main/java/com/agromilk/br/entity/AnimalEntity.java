package com.agromilk.br.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ANIMAL")
@Data
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANIMAL")
    private Long idAnimal;

    @NotNull
    @Column(name = "CODIGO")
    private String codigo;
    @NotNull
    @Column(name = "APELIDO")
    private String apelido;
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;
    @NotNull
    @Column(name = "DATA_COMPRA")
    private Date dataCompra;
    @Column(name = "COR")
    @NotNull
    private String cor;

}
