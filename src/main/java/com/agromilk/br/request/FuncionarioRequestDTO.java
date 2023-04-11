package com.agromilk.br.request;

import com.agromilk.br.security.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FuncionarioRequestDTO {


    private Long idFuncionario;

    @NotEmpty(message = "Nome é obrigatório")
    private String nomeFuncionario;

    @CPF(message = "CPF invalido")
    private String cpf;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate dataNascimento;

    @NotEmpty(message = "Endereço é obrigatório")
    private String endereco;

    private String telefone;

    @NotEmpty(message = "Senha é obrigatório")
    private String senha;

    @NotEmpty(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    private Perfil perfil;

    private List<Integer> perfis;

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis.stream().map(Perfil::getCod).collect(Collectors.toList());
    }

}
