package com.agromilk.br.service;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.request.MarcaRequestDTO;
import javassist.NotFoundException;

import java.util.List;

public interface MarcaService {

    void excluir(Long idMarca) throws Exception;

    MarcaEntity salvar(MarcaRequestDTO dto) throws NotFoundException;

    MarcaEntity atualizar(MarcaRequestDTO dto, Long idMarca) throws Exception;

}
