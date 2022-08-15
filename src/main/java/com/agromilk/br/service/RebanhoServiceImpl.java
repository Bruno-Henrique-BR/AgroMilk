package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.RebanhoEntity;
import com.agromilk.br.repository.RebanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RebanhoServiceImpl implements RebanhoService {

    @Autowired
    private RebanhoRepository rebanhoRepository;

    public RebanhoServiceImpl(RebanhoRepository rebanhoRepository) {
        this.rebanhoRepository = rebanhoRepository;
    }


    @Override
    public RebanhoEntity salvar(RebanhoEntity rebanho) {
        return rebanhoRepository.save(rebanho);
    }

    @Override
    public List<RebanhoEntity> listar() throws RuntimeException {
        return rebanhoRepository.findAll();

    }
    


    @Override
    public void excluir(Long idRebanho) throws Exception {
        if (rebanhoRepository.validadeDeleteRebanho(idRebanho) != null)
           {
            throw new Exception("Rebanho contem animais");
        }
        rebanhoRepository.deleteById(idRebanho);
    }
}
