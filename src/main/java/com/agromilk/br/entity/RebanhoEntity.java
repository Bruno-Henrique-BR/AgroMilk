package com.agromilk.br.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "REBANHO")
@Data
@RequiredArgsConstructor
public class RebanhoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REBANHO")
    private Long idRebanho;
    private String nome;
    private String descricao;
    @JsonIgnore
    @OneToMany(mappedBy = "rebanho")
    private List<AnimalEntity> list = new ArrayList<>();
}
