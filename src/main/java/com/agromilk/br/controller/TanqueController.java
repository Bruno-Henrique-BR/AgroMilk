package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.service.TanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("agromilk/tanque")
public class TanqueController {

    @Autowired
    private TanqueService tanqueService;

    public TanqueController(TanqueService tanqueService) {
        this.tanqueService = tanqueService;
    }

    @PostMapping
    public ResponseEntity<TanqueEntity> cadastrar(@RequestBody @Valid TanqueEntity tanque){
        return ResponseEntity.status(HttpStatus.CREATED).body(tanqueService.salvar(tanque));
    }
    @GetMapping
    public ResponseEntity<List<TanqueEntity>> listar(){
        List<TanqueEntity> tanques = tanqueService.listar();
        return ResponseEntity.ok(tanques);
    }
}
