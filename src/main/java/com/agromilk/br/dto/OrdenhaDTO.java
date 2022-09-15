package com.agromilk.br.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdenhaDTO {

    private Long idOrdenha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime data;

    private Double quantidade;

    private AnimalDTO animal;

    private TanqueDTO tanque;

    private FuncionarioDTO funcionario;
}
