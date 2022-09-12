package com.agromilk.br.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class FuncionarioDTO {


    private Long idFuncionario;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String endereco;

    private String telefone;

}
