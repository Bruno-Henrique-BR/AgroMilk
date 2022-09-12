package com.agromilk.br.service;

import com.agromilk.br.dto.TanqueDTO;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.request.RacaRequestDTO;
import com.agromilk.br.request.TanqueRequestDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TanqueService {

    void excluir(Long idTanque) throws Exception;


    Page<TanqueEntity> listar(
            Long idTanque,
            String descricao,
            Double capacidade,
            MarcaEntity nomeMarca,
            String modelo,
            LocalDate dataFabricacao,
            Boolean ativo,
            Pageable pageable) throws Exception;

    TanqueEntity salvar(TanqueRequestDTO dto) throws NotFoundException;

    TanqueEntity atualizar(TanqueRequestDTO dto, Long idTanque) throws Exception;
}
