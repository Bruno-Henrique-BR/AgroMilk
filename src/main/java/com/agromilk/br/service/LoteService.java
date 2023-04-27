package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.request.LoteRequestDTO;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoteService {


    List<LoteEntity> listar(
            Long idLote,
            String nome,
            String descricao,
            Pageable pageable) throws Exception;
    //void adicionarAnimalEMovimento(Long idLote, Long idAnimal) throws Exception;
    void adicionarAnimalEMovimento(LoteEntity lote, Long idAnimal) throws Exception;
    void adicionarAnimaisEMovimento(Long idLote, List<Long> idAnimais) throws Exception;
    void excluir(Long idLote) throws Exception;

    LoteEntity salvar(LoteRequestDTO dto) throws NotFoundException;

    LoteEntity atualizar(LoteRequestDTO dto, Long idLote) throws Exception;

    LoteEntity findById( Long idLote) throws ObjectNotFoundException, javassist.tools.rmi.ObjectNotFoundException;
    void adicionarAnimal(Long idLote, AnimalEntity animal) throws javassist.tools.rmi.ObjectNotFoundException;

    Long lotes();
}