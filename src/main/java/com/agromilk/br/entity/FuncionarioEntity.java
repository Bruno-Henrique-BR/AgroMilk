package com.agromilk.br.entity;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "FUNCIONARIO")
@Data
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Long idFuncionario;

    private String nome;

    @CPF
    private String cpf;

    private Date dataNascimento;

    private String endereco;

    private String telefone;
}
