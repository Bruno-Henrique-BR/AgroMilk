package com.agromilk.br.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RECEITA")
@Data
public class ReceitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECEITA")

    private Long IdReceita;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Double valor;

    @ManyToOne @JoinColumn(name = "ID_TIPO_OPERACAO",referencedColumnName = "ID_TIPO_OPERACAO")
    private TipoOperacaoEntity tipoOperacao;

    @ManyToOne @JoinColumn(name = "ID_USUARIO",referencedColumnName = "ID_USUARIO")
    private UsuarioEntity usuario;


    @ManyToOne @JoinColumn(name = "ID_PESSOA",referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;

}
