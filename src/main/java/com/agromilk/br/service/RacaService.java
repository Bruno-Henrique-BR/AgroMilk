package com.agromilk.br.service;

import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.request.RacaRequestDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RacaService {


    Page<RacaEntity> listar(
            Long idRaca,
            String nome,
            String descricao,
            Pageable pageable) throws Exception;

    void excluir(Long idRaca) throws Exception;

    RacaEntity salvar(RacaRequestDTO dto) throws NotFoundException;

    RacaEntity atualizar(RacaRequestDTO dto, Long idRaca) throws Exception;

}
