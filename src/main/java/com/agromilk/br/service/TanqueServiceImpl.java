package com.agromilk.br.service;

import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.repository.TanqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TanqueServiceImpl implements TanqueService {

    @Autowired
    private TanqueRepository tanqueRepository;

    @Override
    public TanqueEntity salvar(TanqueEntity tanque) {
        return tanqueRepository.save(tanque);
    }

    @Override
    public List<TanqueEntity> listar() throws RuntimeException {
        return tanqueRepository.findAll();

    }
}
