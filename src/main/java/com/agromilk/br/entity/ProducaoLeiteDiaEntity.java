package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "PRODUCAO_LEITE")
@Data
public class ProducaoLeiteDiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCAO_LEITE")
    private Long idProducaoLeite;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
    private Double leiteTanque;
    private Double descarte;
    @OneToOne
    @JoinColumn(name = "ID_TANQUE", referencedColumnName = "ID_TANQUE")
    private TanqueEntity tanque;
    private Double totalProduzido;

}
