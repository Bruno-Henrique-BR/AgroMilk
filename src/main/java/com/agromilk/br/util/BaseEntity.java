package com.agromilk.br.util;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity<TID> implements Serializable {
    private static final long serialVersionUID = 2421756363548167258L;

    @Transient
    public abstract TID getId();

    @Basic(optional = true)
    @Column(name = "DH_UPDATE")
    protected LocalDateTime dhUpdate;

    @Basic(optional = true)
    @Column(name = "DH_DELETE")
    protected LocalDateTime dhDelete;



    @PreUpdate
    public void preUpdate() {
        this.dhUpdate = LocalDateTime.now();
    }

    @PreRemove
    public void preRemove() {
        this.dhDelete = LocalDateTime.now();
    }

    public Boolean isDeleted() {
        return this.dhDelete == null;
    }

    protected boolean isNull(Object obj) {
        return obj == null;
    }

    public Boolean isIdNull() {
        return this.getId() == null;
    }
}
