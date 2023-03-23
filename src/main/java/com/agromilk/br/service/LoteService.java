package com.agromilk.br.service;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.request.LoteRequestDTO;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoteService {


    List<LoteEntity> listar(
            Long idLote,
            String nome,
            String descricao,
            Pageable pageable) throws Exception;

    void excluir(Long idLote) throws Exception;

    LoteEntity salvar(LoteRequestDTO dto) throws NotFoundException;

    LoteEntity atualizar(LoteRequestDTO dto, Long idLote) throws Exception;

    LoteEntity findById( Long idLote) throws ObjectNotFoundException, javassist.tools.rmi.ObjectNotFoundException;

}