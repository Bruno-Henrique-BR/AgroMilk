package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.request.AnimalRequestDTO;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.request.TanqueRequestDTO;
import javassist.NotFoundException;

import java.util.List;

public interface AnimalService {

    AnimalEntity salvar(AnimalRequestDTO dto) throws NotFoundException;

}
