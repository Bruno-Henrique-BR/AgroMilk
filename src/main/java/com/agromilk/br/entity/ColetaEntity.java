package com.agromilk.br.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "COLETA")
@Data
public class ColetaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_COLETA")
        private Long idColeta;
        @ManyToOne
        @JoinColumn(name = "ID_TANQUE", referencedColumnName = "ID_TANQUE")
        private TanqueEntity idTanque;
        @ManyToOne
        @JoinColumn(name = "ID_LATICINIO", referencedColumnName = "ID_PESSOA")
        private PessoaEntity laticinio;
        @ManyToOne
        @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID_PESSOA")
        private PessoaEntity funcionario;
        private String descricao;
        private Double litros;
        private LocalDate data;
        private Time hora;

}
