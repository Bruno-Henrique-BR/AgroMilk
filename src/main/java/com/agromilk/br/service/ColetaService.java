package com.agromilk.br.service;


import com.agromilk.br.entity.ColetaEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.request.ColetaRequestDTO;
import com.agromilk.br.request.OrdenhaRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ColetaService {

    List<ColetaEntity> listar(
            Long idColeta,
            Long idTanque,
            Long idLaticinio,
            Double quantidade,
            String descricao,
            LocalDate data,
            Pageable pageable) throws Exception;
    void excluir(Long idColeta) throws Exception;
    ColetaEntity salvar(ColetaRequestDTO dto) throws Exception;

    ColetaEntity atualizar(ColetaRequestDTO dto, Long idColeta) throws Exception;

}
