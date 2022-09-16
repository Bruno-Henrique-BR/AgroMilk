package com.agromilk.br.dto;

import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class ColetaDTO {

    private Long idColeta;

    private TanqueEntity tanque;

    private LaticinioEntity laticinio;

    private Double quantidade;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime data;
}
