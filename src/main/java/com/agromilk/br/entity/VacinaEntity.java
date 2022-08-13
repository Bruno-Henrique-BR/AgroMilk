package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VACINA")
@Data
public class VacinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VACINA")
    private Long idVacina;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ANIMAL", nullable = false)
    private AnimalEntity idAnimal;

    private Date data;
}
