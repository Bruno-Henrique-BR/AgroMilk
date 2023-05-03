package com.agromilk.br.service;

import com.agromilk.br.dto.MovimentoDTO;
import com.agromilk.br.entity.MovimentoEntity;
import com.agromilk.br.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MovimentoServiceImpl implements  MovimentoService{
    @Autowired
    private  MovimentoRepository movimentoRepository;

    public MovimentoServiceImpl(MovimentoRepository movimentoRepository) {
        this.movimentoRepository = movimentoRepository;

    }

    public List<MovimentoDTO> getMovimentosByAnimal(Long idAnimal) {
        List<MovimentoEntity> movimentos = movimentoRepository.findByAnimalId(idAnimal);
        return movimentos.stream()
                .map(MovimentoDTO::new)
                .collect(Collectors.toList());
    }

}
