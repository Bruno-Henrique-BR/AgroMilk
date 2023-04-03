package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LaticinioEntity;
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

    public LaticinioDTO() {
        super();
    }

    public LaticinioDTO(LaticinioEntity obj) {
        super();
        this.idLaticinio = obj.getIdLaticinio();
        this.razaoSocial = obj.getRazaoSocial();
        this.cnpj = obj.getCnpj();
        this.endereco = obj.getEndereco();
        this.telefone = obj.getTelefone();


    }
}
