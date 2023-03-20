package com.agromilk.br.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LOTE")
@Data
public class LoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOTE")
    private Long idLote;
    private String nomeLote;
    private String descricao;
    @JsonIgnore
    @OneToMany(mappedBy = "lote")
    private List<AnimalEntity> list = new ArrayList<>();
}
