package com.agromilk.br.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class OrdenhaRequestDTO {


    private Long idOrdenha;

    @NotNull(message = "Data de ordenha é obrigatorio")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate data;
    @NotNull(message = "Quantidade é obrigatorio")
    private Double quantidade;
    @NotNull(message = "Animal é obrigatorio")
    private Long idAnimal;
    @NotNull(message = "Tanque é obrigatorio")
    private Long idTanque;


}
