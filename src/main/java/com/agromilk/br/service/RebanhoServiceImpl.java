package com.agromilk.br.service;

import com.agromilk.br.entity.RebanhoEntity;
import com.agromilk.br.repository.RebanhoRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RebanhoServiceImpl implements RebanhoService {

    @Autowired
    private RebanhoRepository rebanhoRepository;


    @Override
    public RebanhoEntity salvar(RebanhoEntity rebanho) {
        return rebanhoRepository.save(rebanho);
    }

    @Override
    public List<RebanhoEntity> listar() throws RuntimeException {
        return rebanhoRepository.findAll();

    }

    public RebanhoEntity findById(Long idRebanho) throws ObjectNotFoundException {
        Optional<RebanhoEntity> obj = rebanhoRepository.findById(idRebanho);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idRebanho + ", Tipo: " + RebanhoEntity.class.getName()));
    }
    


    @Override
    public void excluir(Long idRebanho) throws Exception {
        RebanhoEntity obj = findById(idRebanho);

        if (obj.getList().size() > 0) {
            throw new Exception("Rebanho possui animais, não pode ser deletado!");
        }
        rebanhoRepository.deleteById(idRebanho);
    }
}
