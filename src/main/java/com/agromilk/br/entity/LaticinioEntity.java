package com.agromilk.br.entity;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LATICINIO")
@Data
public class LaticinioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LATICINIO")
    private Long idLaticinio;

    private String razaoSocial;

    @CNPJ
    private String cnpj;

    private String endereco;

    private String telefone;
}
