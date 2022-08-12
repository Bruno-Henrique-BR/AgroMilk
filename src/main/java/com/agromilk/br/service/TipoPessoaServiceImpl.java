package com.agromilk.br.service;

import com.agromilk.br.entity.PessoaEntity;
import com.agromilk.br.entity.TipoPessoaEntity;
import com.agromilk.br.repository.PessoaRepository;
import com.agromilk.br.repository.TanqueRepository;
import com.agromilk.br.repository.TipoPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoPessoaServiceImpl implements TipoPessoaService {

    @Autowired
    private TipoPessoaRepository tipoPessoaRepository;

    @Override
    public TipoPessoaEntity salvar(TipoPessoaEntity tipoPessoa) {
        return tipoPessoaRepository.save(tipoPessoa);
    }
}
