package com.agromilk.br.service;

import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.repository.OrdenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@Transactional
public class OrdenhaServiceImpl implements OrdenhaService {

    @Autowired
    private OrdenhaRepository ordenhaRepository;

    public OrdenhaServiceImpl(OrdenhaRepository ordenhaRepository) {
        this.ordenhaRepository = ordenhaRepository;
    }

    @Override
    public OrdenhaEntity salvar(OrdenhaEntity ordenha) {
        return ordenhaRepository.save(ordenha);
    }
    @Override
    public List<OrdenhaEntity> listar() throws RuntimeException {
        return ordenhaRepository.findAll();

    }
}
