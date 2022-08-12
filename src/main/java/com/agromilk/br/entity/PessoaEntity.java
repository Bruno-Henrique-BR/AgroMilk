package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "PESSOA")
@Data
public class PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TANQUE")
    private Long idPessoa;

    private String nome;

    private String cpfCnpj;

    private Date dataNascimento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tipo_pessoa", nullable = false)
    private TipoPessoaEntity tipoPessoaId;
}
