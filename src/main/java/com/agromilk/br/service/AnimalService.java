package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;

import java.util.List;

public interface AnimalService {

    AnimalEntity salvar(AnimalEntity animal);
    List<AnimalEntity> listar();

}
