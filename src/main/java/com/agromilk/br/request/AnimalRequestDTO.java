package com.agromilk.br.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class AnimalRequestDTO {


        private Long idAnimal;
        @NotNull(message = "Codigo é obrigatório")
        private String codigo;

        @NotNull(message = "Apelido é obrigatório")
        private String apelido;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate dataNascimento;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate dataCompra;

        @NotNull(message = "Cor é obrigatório")
        private String cor;

        @NotNull(message = "Lote é obrigatório")
        private Long idLote;

        @NotNull(message = "Raça é obrigatório")
        private Long idRaca;


}
