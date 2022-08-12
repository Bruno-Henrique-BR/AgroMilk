package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.service.AnimalService;
import com.agromilk.br.service.TanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk/tanque")
public class TanqueController {

    @Autowired
    private TanqueService tanqueService;
    @PostMapping
    public ResponseEntity<TanqueEntity> cadastrar(@RequestBody @Valid TanqueEntity tanque){
        return ResponseEntity.status(HttpStatus.CREATED).body(tanqueService.salvar(tanque));
    }
}
