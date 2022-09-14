package com.agromilk.br.service;


import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.request.AnimalRequestDTO;
import com.agromilk.br.request.OrdenhaRequestDTO;
import javassist.NotFoundException;

import java.util.List;

public interface OrdenhaService {

    void excluir(Long idOrdenha) throws Exception;
    OrdenhaEntity salvar(OrdenhaRequestDTO dto) throws NotFoundException;

    OrdenhaEntity atualizar(OrdenhaRequestDTO dto, Long idOrdenha) throws Exception;

}
