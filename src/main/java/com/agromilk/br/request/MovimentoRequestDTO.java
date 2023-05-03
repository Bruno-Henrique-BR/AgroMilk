package com.agromilk.br.request;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LoteEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovimentoRequestDTO {

    private Long id;

    private AnimalEntity animal;

    private LoteEntity lote;

    private LocalDate dataEntrada;

    private LocalDate dataSaida;

    private Long dias;
}
