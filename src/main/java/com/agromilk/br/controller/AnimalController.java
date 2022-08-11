package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @PostMapping
    public ResponseEntity<AnimalEntity> cadastrar(@RequestBody @Valid AnimalEntity animal){
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.salvar(animal));
    }
}
