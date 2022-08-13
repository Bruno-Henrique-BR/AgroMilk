package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("agromilk/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalEntity> cadastrarAnimal(@RequestBody @Valid AnimalEntity animal){
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.salvar(animal));
    }
    @GetMapping
    public ResponseEntity<List<AnimalEntity>> listarAnimais(){
        List<AnimalEntity> animais = animalService.listar();
        return ResponseEntity.ok(animais);
    }
}
