package com.agromilk.br.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class FuncionarioRequestDTO {


    private Long idFuncionario;

    @NotEmpty(message = "Nome é obrigatório")
    private String nomeFuncionario;

    @CPF(message = "CPF invalido")
    private String cpf;
    @NotNull(message = "Data de ordenha é obrigatorio")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDate dataNascimento;

    @NotEmpty(message = "Endereço é obrigatório")
    private String endereco;

    @NotEmpty(message = "Telefone é obrigatório")
    private String telefone;

}
