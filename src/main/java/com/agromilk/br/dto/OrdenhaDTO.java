package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrdenhaDTO {

    private Long idOrdenha;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private LocalDate data;

    private Double primeiraOrdenha;
    private Double segundaOrdenha;

    private Long animal;

    private Long tanque;

    public OrdenhaDTO() {
        super();
    }

    public OrdenhaDTO(OrdenhaEntity obj) {
        super();
        this.idOrdenha = obj.getIdOrdenha();
        this.data = obj.getData();
        this.primeiraOrdenha = obj.getPrimeiraOrdenha();
        this.segundaOrdenha = obj.getSegundaOrdenha();
        this.animal = obj.getAnimal().getIdAnimal();
        this.tanque = obj.getTanque().getIdTanque();

    }
}
