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
        @OneToOne
        @JoinColumn(name = "ID_TANQUE", referencedColumnName = "ID_TANQUE")
        private TanqueEntity idTanque;
        private String descricao;
        private Double litros;
        private LocalDate data;
        private Time hora;

}
