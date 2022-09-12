package com.agromilk.br.service;


import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.request.FuncionarioRequestDTO;
import com.agromilk.br.request.LoteRequestDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface FuncionarioService {
    Page<FuncionarioEntity> listar(
            Long idFuncionario,
            String nome,
            String cpf,
            LocalDate dataNascimento,
            String endereco,
            String telefone,
            Pageable pageable) throws Exception;

    void excluir(Long idFuncionario) throws Exception;

    FuncionarioEntity salvar(FuncionarioRequestDTO dto) throws NotFoundException;

    FuncionarioEntity atualizar(FuncionarioRequestDTO dto, Long idFuncionario) throws Exception;

}
