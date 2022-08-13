package com.agromilk.br.service;

import com.agromilk.br.entity.RebanhoEntity;

import java.util.List;

public interface RebanhoService {

    RebanhoEntity salvar(RebanhoEntity rebanho);
    List<RebanhoEntity> listar();

    void excluir(Long idRebanho) throws Exception;
}
