package com.agromilk.br.dto;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.MovimentoEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode()
public class MovimentoDTO {

    private Long id;

    private AnimalEntity animal;

    private LoteEntity lote;

    private LocalDate dataEntrada;

    private LocalDate dataSaida;

    private Integer dias;

    public MovimentoDTO(MovimentoEntity obj) {
        super();
        this.id = obj.getId();
        this.animal = obj.getAnimal();
        this.lote = obj.getLote();
        this.dataEntrada = obj.getDataEntrada();
        this.dataSaida = obj.getDataSaida();

        // Calcula a diferença em dias
        if (dataSaida != null) {
            this.dias = (int) ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        } else {
            // Caso a data de saída seja nula, define dias como null
            this.dias = null;
        }
    }

}
