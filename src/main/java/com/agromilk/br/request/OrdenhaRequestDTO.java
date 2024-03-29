package com.agromilk.br.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class OrdenhaRequestDTO {


    private Long idOrdenha;



    @NotNull(message = "Data de ordenha é obrigatório")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate data;
    @NotNull(message = "Quantidade é obrigatorio")
    private Double primeiraOrdenha;
    private Double segundaOrdenha;
    @NotNull(message = "Animal é obrigatorio")
    private Long idAnimal;
    private Long idTanque;


}
