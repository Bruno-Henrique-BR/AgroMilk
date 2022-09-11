package com.agromilk.br.service;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.request.LoteRequestDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoteService {


    Page<LoteEntity> listar(
            Long idLote,
            String nome,
            String descricao,
            String sexo,
            Pageable pageable) throws Exception;

    void excluir(Long idLote) throws Exception;

    LoteEntity salvar(LoteRequestDTO dto) throws NotFoundException;

    LoteEntity atualizar(LoteRequestDTO dto, Long idLote) throws Exception;
}
