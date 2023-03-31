package com.agromilk.br.request;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class OrdenhaRequestDTO {


    private Long idOrdenha;

    @NotNull(message = "Data de ordenha é obrigatorio")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDate data;
    @NotNull(message = "Quantidade é obrigatorio")
    private Double quantidade;
    @NotNull(message = "Animal é obrigatorio")
    private Long idAnimal;
    @NotNull(message = "Tanque é obrigatorio")
    private Long idTanque;


}
