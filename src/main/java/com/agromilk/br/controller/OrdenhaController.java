package com.agromilk.br.controller;

import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.service.OrdenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("agromilk/ordenha")
public class OrdenhaController {

    @Autowired
    private OrdenhaService ordenhaService;

    public OrdenhaController(OrdenhaService ordenhaService) {
        this.ordenhaService = ordenhaService;
    }

    @PostMapping
    public ResponseEntity<OrdenhaEntity> cadastrarOrdenha(@RequestBody @Valid OrdenhaEntity ordenha){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenhaService.salvar(ordenha));
    }

    @GetMapping
    public ResponseEntity<List<OrdenhaEntity>> listarOrdenha(){
        List<OrdenhaEntity> ordenhas = ordenhaService.listar();
        return ResponseEntity.ok(ordenhas);
    }
}
