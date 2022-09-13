package com.agromilk.br.service;


import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.request.FuncionarioRequestDTO;
import com.agromilk.br.request.LaticinioRequestDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface LaticinioService {
    Page<LaticinioEntity> listar(
            Long idLaticinio,
            String razaoSocial,
            String cnpj,
            String endereco,
            String telefone,
            Pageable pageable) throws Exception;

    void excluir(Long idLaticinio) throws Exception;

    LaticinioEntity salvar(LaticinioRequestDTO dto) throws NotFoundException;

    LaticinioEntity atualizar(LaticinioRequestDTO dto, Long idLaticinio) throws Exception;
}
