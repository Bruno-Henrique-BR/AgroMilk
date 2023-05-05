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

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_LOTE")
    private TipoLoteEnum tipoLote;

    @JsonIgnore
    @OneToMany(mappedBy = "lote")
    private List<AnimalEntity> list = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "lote")
    private List<AnimalEntity> animais = new ArrayList<>();

    // getters and setters for animais
    public List<AnimalEntity> getAnimais() {
        return animais;
    }

    public void setAnimais(List<AnimalEntity> animais) {
        this.animais = animais;
    }

}
