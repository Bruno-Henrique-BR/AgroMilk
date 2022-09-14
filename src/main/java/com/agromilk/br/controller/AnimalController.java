package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.request.AnimalRequestDTO;
import com.agromilk.br.request.TanqueRequestDTO;
import com.agromilk.br.service.AnimalService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("agromilk/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalEntity> cadastrarAnimal(
            @RequestBody @Valid AnimalRequestDTO dto)
            throws NotFoundException, BadRequestException {
        AnimalEntity response = animalService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }
}
