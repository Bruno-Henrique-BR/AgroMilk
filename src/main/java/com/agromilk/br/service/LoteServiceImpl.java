package com.agromilk.br.service;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.repository.LoteRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository loteRepository;


    @Override
    public LoteEntity salvar(LoteEntity lote) {
        return loteRepository.save(lote);
    }

    @Override
    public List<LoteEntity> listar() throws RuntimeException {
        return loteRepository.findAll();

    }

    public LoteEntity findById(Long idLote) throws ObjectNotFoundException {
        Optional<LoteEntity> obj = loteRepository.findById(idLote);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idLote + ", Tipo: " + LoteEntity.class.getName()));
    }
    


    @Override
    public void excluir(Long idLote) throws Exception {
        LoteEntity obj = findById(idLote);

        if (obj.getList().size() > 0) {
            throw new Exception("O Lote possui animais, não pode ser deletado!");
        }
        loteRepository.deleteById(idLote);
    }
}
