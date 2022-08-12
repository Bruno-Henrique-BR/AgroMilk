package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.PessoaEntity;
import com.agromilk.br.service.AnimalService;
import com.agromilk.br.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    @PostMapping
    public ResponseEntity<PessoaEntity> cadastrar(@RequestBody @Valid PessoaEntity pessoa){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(pessoa));
    }
}
