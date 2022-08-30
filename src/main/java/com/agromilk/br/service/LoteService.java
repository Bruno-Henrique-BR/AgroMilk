package com.agromilk.br.service;

import com.agromilk.br.entity.LoteEntity;

import java.util.List;

public interface LoteService {

    LoteEntity salvar(LoteEntity lote);
    List<LoteEntity> listar();

    void excluir(Long idLote) throws Exception;
}
