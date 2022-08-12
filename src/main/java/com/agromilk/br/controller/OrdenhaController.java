package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.service.AnimalService;
import com.agromilk.br.service.OrdenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk/ordenha")
public class OrdenhaController {

    @Autowired
    private OrdenhaService ordenhaService;
    @PostMapping
    public ResponseEntity<OrdenhaEntity> cadastrar(@RequestBody @Valid OrdenhaEntity ordenha){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenhaService.salvar(ordenha));
    }
}
