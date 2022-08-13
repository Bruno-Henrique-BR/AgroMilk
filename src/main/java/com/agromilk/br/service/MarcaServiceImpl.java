package com.agromilk.br.service;

import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public MarcaEntity salvar(MarcaEntity marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public List<MarcaEntity> listar() throws RuntimeException {
        return marcaRepository.findAll();

    }
}
