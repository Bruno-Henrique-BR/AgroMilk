package com.agromilk.br.controller;

import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.service.MarcaService;
import com.agromilk.br.service.TanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("agromilk/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping
    public ResponseEntity<MarcaEntity> cadastrar(@RequestBody @Valid MarcaEntity marca){
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaService.salvar(marca));
    }
    @GetMapping
    public ResponseEntity<List<MarcaEntity>> listar(){
        List<MarcaEntity> marcas = marcaService.listar();
        return ResponseEntity.ok(marcas);
    }
}
