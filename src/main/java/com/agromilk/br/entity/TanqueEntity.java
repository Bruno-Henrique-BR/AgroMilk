package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "TANQUE")
@Data
public class TanqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TANQUE")
    private Long idTanque;

    private String descricao;

    private Double capacidade = 0.0;

    private String modelo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFabricacao;

    private Double quantidadeAtual;


}
