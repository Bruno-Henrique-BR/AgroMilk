package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.TanqueEntity;

import java.util.List;

public interface TanqueService {
    TanqueEntity salvar(TanqueEntity tanque);
    List<TanqueEntity> listar();
}
