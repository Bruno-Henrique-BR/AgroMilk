package com.agromilk.br.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANIMAL_REPRODUCAO")
@Data
@RequiredArgsConstructor
public class AnimalReproducaoEntity extends  AnimalEntity{
    @OneToOne
    @JoinColumn(name = "ID_ANIMAL_MAE",referencedColumnName = "ID_ANIMAL")
    private AnimalEntity idAnimalPai;
    @OneToOne
    @JoinColumn(name = "ID_ANIMA_PAIL",referencedColumnName = "ID_ANIMAL")
    private AnimalEntity idAnimalMae;

}
