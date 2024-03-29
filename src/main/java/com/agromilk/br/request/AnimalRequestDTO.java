package com.agromilk.br.request;

import com.agromilk.br.repository.OrdenhaRepository;
import com.agromilk.br.util.LenientLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class AnimalRequestDTO {

        private OrdenhaRepository ordenhaRepository;
        private Long idAnimal;
        @NotNull(message = "Codigo é obrigatório")
        private String codigo;

        private String apelido;

        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
        private LocalDate dataNascimento;


        @JsonDeserialize(using = LenientLocalDateDeserializer.class)
        @JsonSerialize(using = ToStringSerializer.class)
        @JsonInclude(JsonInclude.Include.ALWAYS)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
        private LocalDate dataCompra;


        @NotNull(message = "Lote é obrigatório")
        private Long idLote;

        @NotNull(message = "Raça é obrigatório")
        private Long idRaca;

        private Double mediaAnimal;


}
