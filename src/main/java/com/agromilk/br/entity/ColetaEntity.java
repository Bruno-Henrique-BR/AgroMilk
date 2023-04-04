package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        private TanqueEntity tanque;
        @ManyToOne
        @JoinColumn(name = "ID_LATICINIO", referencedColumnName = "ID_LATICINIO")
        private LaticinioEntity laticinio;

        @NotNull(message = "Quantidade é obrigatorio")
        private Double quantidade;

        private String descricao;
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
        private LocalDate data;


}
