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
    @Column(name = "ID_PESSOA")
    private Long idPessoa;

    private String nome;

    private String cpfCnpj;

    private Date dataNascimento;

    private String sexo;

    private String endereco;

    private String telefone;
    @OneToOne
    @JoinColumn(name = "ID_TIPO_PESSOA", referencedColumnName = "ID_TIPO_PESSOA")
    private TipoPessoaEntity tipoPessoaId;
}
