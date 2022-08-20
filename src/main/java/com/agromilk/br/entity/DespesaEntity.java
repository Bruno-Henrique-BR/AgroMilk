package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "DESPESA")
@Data
public class DespesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DESPESA")
    Long idDespesa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate data;

    Double valor;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_OPERACAO",referencedColumnName = "ID_TIPO_OPERACAO")
    TipoOperacaoEntity tipoOperacao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO",referencedColumnName = "ID_USUARIO")
    UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA",referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;
}
