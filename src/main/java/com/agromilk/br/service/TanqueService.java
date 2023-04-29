package com.agromilk.br.service;

import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.request.TanqueRequestDTO;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TanqueService {

    void excluir(Long idTanque) throws Exception;


    List<TanqueEntity> listar(
            Long idTanque,
            String descricao,
            Double capacidade,
            String modelo,
            LocalDate dataFabricacao,
            Pageable pageable) throws Exception;

    TanqueEntity salvar(TanqueRequestDTO dto) throws NotFoundException;

    TanqueEntity atualizar(TanqueRequestDTO dto, Long idTanque) throws Exception;

    TanqueEntity findById( Long idTanque) throws ObjectNotFoundException;

    Long tanques();

    Double quantidadeTotalLeite();
}
