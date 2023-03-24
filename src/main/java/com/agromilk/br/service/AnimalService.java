package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.request.AnimalRequestDTO;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AnimalService {



    List<AnimalEntity> listar(
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

    AnimalEntity findById(Long idAnimal) throws ObjectNotFoundException, javassist.tools.rmi.ObjectNotFoundException;


    Long animais();

    Long animaisLactacao();

    Long animaisSeca();

    Double animaisMediaLitro();

    Double porcentagemLactantes();

    Double porcentagemSecas();

    Page<AnimalEntity> listarAnimais(
            Pageable pageable) throws Exception;
}
