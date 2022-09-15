package com.agromilk.br.service;


import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.request.OrdenhaRequestDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrdenhaService {

    Page<OrdenhaEntity> listar(
            Long idOrdenha,
            LocalDate data,
            Long quantidade,
            Long idAnimal,
            Long idTanque,
            String nomeFuncionario,
            Pageable pageable) throws Exception;
    void excluir(Long idOrdenha) throws Exception;
    OrdenhaEntity salvar(OrdenhaRequestDTO dto) throws NotFoundException;

    OrdenhaEntity atualizar(OrdenhaRequestDTO dto, Long idOrdenha) throws Exception;

}
