package com.agromilk.br.service;

import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public FuncionarioEntity salvar(FuncionarioEntity funcionario) {
        return funcionarioRepository.save(funcionario);
    }
}
