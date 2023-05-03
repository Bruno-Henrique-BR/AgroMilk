package com.agromilk.br.service;

import com.agromilk.br.dto.MovimentoDTO;
import com.agromilk.br.entity.AnimalEntity;

import java.util.List;

public interface MovimentoService {
    List<MovimentoDTO> getMovimentosByAnimal(Long idAnimal);

    }
