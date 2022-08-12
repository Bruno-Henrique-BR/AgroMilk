package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.PessoaEntity;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public PessoaEntity salvar(PessoaEntity pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
