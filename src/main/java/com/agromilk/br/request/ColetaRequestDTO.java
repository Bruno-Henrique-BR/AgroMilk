package com.agromilk.br.request;

import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime data;
}
