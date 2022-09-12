package com.agromilk.br.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARCA")
@Data
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MARCA")
    private Long idMarca;
    private String nomeMarca;
    @JsonIgnore
    @OneToMany(mappedBy = "marca")
    private List<TanqueEntity> list = new ArrayList<>();
}
