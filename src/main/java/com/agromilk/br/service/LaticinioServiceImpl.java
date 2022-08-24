package com.agromilk.br.service;

import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.repository.LaticinioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LaticinioServiceImpl implements LaticinioService {

    @Autowired
    private LaticinioRepository laticinioRepository;

    @Override
    public LaticinioEntity salvar(LaticinioEntity laticinio) {
        return laticinioRepository.save(laticinio);
    }
}
