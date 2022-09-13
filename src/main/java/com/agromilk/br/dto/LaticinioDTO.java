package com.agromilk.br.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@EqualsAndHashCode(callSuper = false)
public class LaticinioDTO {
    private Long idLaticinio;

    private String razaoSocial;

    @CNPJ
    private String cnpj;

    private String endereco;

    private String telefone;
}
