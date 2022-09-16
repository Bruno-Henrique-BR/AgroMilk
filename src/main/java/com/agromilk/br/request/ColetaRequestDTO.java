package com.agromilk.br.request;

import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ColetaRequestDTO {

    private Long idColeta;

    @NotNull(message = "Tanque é obrigatório")
    private Long idTanque;

    @NotNull(message = "Laticinio é obrigatório")
    private Long idLaticinio;

    @NotNull(message = "Quantidade é obrigatorio")
    private Double quantidade;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime data;
}
