package com.agromilk.br.controller;

import com.agromilk.br.entity.TipoPessoaEntity;
import com.agromilk.br.service.TipoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk/tipoPessoa")
public class TipoPessoaController {
    @Autowired
    private TipoPessoaService tipoPessoaService;

    @PostMapping
    public ResponseEntity<TipoPessoaEntity> cadastrar(@RequestBody @Valid TipoPessoaEntity tipoPessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoPessoaService.salvar(tipoPessoa));
    }
}