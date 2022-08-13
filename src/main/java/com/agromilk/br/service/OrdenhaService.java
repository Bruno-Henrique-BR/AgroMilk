package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.entity.OrdenhaEntity;

import java.util.List;

public interface OrdenhaService {
    OrdenhaEntity salvar(OrdenhaEntity ordenha);
    List<OrdenhaEntity> listar();
}
