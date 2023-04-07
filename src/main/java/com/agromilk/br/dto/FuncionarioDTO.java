package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.security.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class FuncionarioDTO implements Serializable {


    private Long idFuncionario;

    private String nomeFuncionario;

    private String cpf;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataNascimento;

    private String endereco;

    private String telefone;

    @Email
    private String email;

    private String senha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
    //private DateTimeFormatter dataCriacao = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public FuncionarioDTO() {
        super();
    }

    public FuncionarioDTO(FuncionarioEntity obj) {
        super();
        this.idFuncionario = obj.getIdFuncionario();
        this.nomeFuncionario = obj.getNomeFuncionario();
        this.cpf = obj.getCpf();
        this.dataNascimento = obj.getDataNascimento();
        this.endereco = obj.getEndereco();
        this.telefone = obj.getTelefone();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = new HashSet<>();
        addPerfil(Perfil.FUNCIONARIO);

    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIL_FUNCIONARIO", joinColumns = @JoinColumn(name = "id_funcionario"))
    private Set<Integer> perfis = new HashSet<>();

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCod());
    }

}
