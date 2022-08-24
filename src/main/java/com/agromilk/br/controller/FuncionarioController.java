package com.agromilk.br.controller;

import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @PostMapping
    public ResponseEntity<FuncionarioEntity> cadastrar(@RequestBody @Valid FuncionarioEntity funcionario){
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.salvar(funcionario));
    }
}
