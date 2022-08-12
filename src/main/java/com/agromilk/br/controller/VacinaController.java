package com.agromilk.br.controller;

import com.agromilk.br.entity.VacinaEntity;
import com.agromilk.br.service.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agromilk/vacina")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;
    @PostMapping
    public ResponseEntity<VacinaEntity> cadastrar(@RequestBody @Valid VacinaEntity vacina){
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaService.salvar(vacina));
    }
}
