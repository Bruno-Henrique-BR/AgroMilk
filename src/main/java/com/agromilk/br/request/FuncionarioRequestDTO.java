package com.agromilk.br.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class FuncionarioRequestDTO {


    private Long idFuncionario;

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @CPF(message = "CPF invalido")
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotEmpty(message = "Endereço é obrigatório")
    private String endereco;

    @NotEmpty(message = "Telefone é obrigatório")
    private String telefone;

}
