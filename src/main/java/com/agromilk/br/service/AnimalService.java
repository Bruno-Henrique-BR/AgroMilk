package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.request.AnimalRequestDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface AnimalService {



    Page<AnimalEntity> listar(
            Long idAnimal,
            String codigo,
            String apelido,
            LocalDate dataNascimento,
            LocalDate dataCompra,
            String cor,
            String nomeLote,
            String nomeRaca,
            Boolean lactacao,
            Pageable pageable) throws Exception;

    void excluir(Long idLote) throws Exception;
    AnimalEntity salvar(AnimalRequestDTO dto) throws NotFoundException;

    AnimalEntity atualizar(AnimalRequestDTO dto, Long idAnimal) throws Exception;

    Long animais();

    Long animaisLactacao();

    Double animaisMediaLitro();
}
