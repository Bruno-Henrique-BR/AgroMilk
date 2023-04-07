package com.agromilk.br.entity;

import com.agromilk.br.dto.FuncionarioDTO;
import com.agromilk.br.security.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "FUNCIONARIO")
@Data
public class FuncionarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Long idFuncionario;

    private String nomeFuncionario;

    @CPF
    private String cpf;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataNascimento;

    private String endereco;

    private String telefone;


    @Column(unique = true)
    protected String email;
    protected String senha;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIL_FUNCIONARIO", joinColumns = @JoinColumn(name = "id_funcionario"))
    private Set<Integer> perfis = new HashSet<>();

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCod());
    }


    public FuncionarioEntity(Long idFuncionario, String nomeFuncionario, String cpf, LocalDate dataNascimento, String endereco, String telefone, String email, String senha) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.perfis = new HashSet<>();
        addPerfil(Perfil.FUNCIONARIO);
    }
    public FuncionarioEntity() {
        super();
        this.perfis = new HashSet<>();
        addPerfil(Perfil.FUNCIONARIO);
    }



}
