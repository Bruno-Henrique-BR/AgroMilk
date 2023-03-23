package com.agromilk.br.service;

import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.exception.NotFoundException;
import com.agromilk.br.request.RacaRequestDTO;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RacaService {


    List<RacaEntity> listar(
            Long idRaca,
            String nome,
            String descricao,
            Pageable pageable) throws Exception;
    void excluir(Long idRaca) throws Exception;
    RacaEntity salvar(RacaRequestDTO dto) throws NotFoundException, javassist.NotFoundException;

    RacaEntity atualizar(RacaRequestDTO dto, Long idRaca) throws Exception;

    RacaEntity findById(Long idRaca) throws ObjectNotFoundException;


}