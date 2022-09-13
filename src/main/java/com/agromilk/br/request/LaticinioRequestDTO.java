package com.agromilk.br.request;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LaticinioRequestDTO {
    private Long idLaticinio;

    @NotEmpty(message = "Razão social é obrigatório")
    private String razaoSocial;

    @CNPJ(message = "CNPJ invalido")
    private String cnpj;

    @NotEmpty(message = "Endereco é obrigatório")
    private String endereco;

    @NotEmpty(message = "Telefone é obrigatório")
    @Size(max = 11, message = "O atributo telefone, possui o tamanho de {max} caractere(s).")
    private String telefone;
}
