package com.agromilk.br.service;

import com.agromilk.br.entity.MarcaEntity;

import java.util.List;

public interface MarcaService {

    MarcaEntity salvar(MarcaEntity marca);
    List<MarcaEntity> listar();

}
