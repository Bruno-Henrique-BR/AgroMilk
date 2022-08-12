package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MARCA")
@Data
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MARCA")
    private Long idMarca;
    private String descricao;
}
