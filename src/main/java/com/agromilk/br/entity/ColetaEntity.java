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
        @JoinColumn(name = "ID_PRODUCAO_LEITE", referencedColumnName = "ID_PRODUCAO_LEITE")
        private ProducaoLeiteDiaEntity producaoLeiteDia;
        @ManyToOne
        @JoinColumn(name = "ID_LATICINIO", referencedColumnName = "ID_LATICINIO")
        private LaticinioEntity laticinio;
        @ManyToOne
        @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID_FUNCIONARIO")
        private FuncionarioEntity funcionario;
        private String descricao;
        private LocalDate data;
        private Time hora;

}
