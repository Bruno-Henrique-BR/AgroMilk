package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "VACINA")
@Data
public class VacinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VACINA")
    private Long idVacina;
    @OneToOne
    @JoinColumn(name = "ID_ANIMAL", referencedColumnName = "ID_ANIMAL")
    private AnimalEntity idAnimal;
    @OneToOne
    @JoinColumn(name = "ID_VACINA_PRODUTO", referencedColumnName = "ID_TIPO_PRODUTO")
    private TipoProdutoEntity vacinaProduto;
    @OneToOne
    @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID_PESSOA")
    private PessoaEntity funcionario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
}
