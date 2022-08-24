package com.agromilk.br.controller;

import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.service.LaticinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk/laticinio")
public class LaticinioController {
    @Autowired
    private LaticinioService laticinioService;

    @PostMapping
    public ResponseEntity<LaticinioEntity> cadastrar(@RequestBody @Valid LaticinioEntity laticinio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laticinioService.salvar(laticinio));
    }
}