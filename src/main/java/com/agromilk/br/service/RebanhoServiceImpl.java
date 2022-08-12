package com.agromilk.br.service;

import com.agromilk.br.entity.PessoaEntity;
import com.agromilk.br.entity.RebanhoEntity;
import com.agromilk.br.repository.PessoaRepository;
import com.agromilk.br.repository.RebanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RebanhoServiceImpl implements RebanhoService {

    @Autowired
    private RebanhoRepository rebanhoRepository;

    @Override
    public RebanhoEntity salvar(RebanhoEntity rebanho) {
        return rebanhoRepository.save(rebanho);
    }
}
