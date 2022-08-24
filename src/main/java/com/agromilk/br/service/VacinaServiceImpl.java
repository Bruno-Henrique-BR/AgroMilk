package com.agromilk.br.service;

import com.agromilk.br.entity.VacinaEntity;
import com.agromilk.br.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VacinaServiceImpl implements VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    @Override
    public VacinaEntity salvar(VacinaEntity vacina) {
        return vacinaRepository.save(vacina);
    }
}
