package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.OrdenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrdenhaServiceImpl implements OrdenhaService {

    @Autowired
    private OrdenhaRepository ordenhaRepository;

    @Override
    public OrdenhaEntity salvar(OrdenhaEntity ordenha) {
        return ordenhaRepository.save(ordenha);
    }
}
