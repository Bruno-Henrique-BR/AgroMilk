package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.FuncionarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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

    private String nomeFuncionario;

    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private String endereco;

    private String telefone;


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


    }

}
