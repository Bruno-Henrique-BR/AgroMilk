package com.agromilk.br.controller;

import com.agromilk.br.entity.RebanhoEntity;
import com.agromilk.br.service.RebanhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("agromilk/rebanho")
public class RebanhoController {

    @Autowired
    private RebanhoService rebanhoService;

    public RebanhoController(RebanhoService rebanhoService) {
        this.rebanhoService = rebanhoService;
    }

    @PostMapping
    public ResponseEntity<RebanhoEntity> cadastrar(@RequestBody @Valid RebanhoEntity rebanho){
        return ResponseEntity.status(HttpStatus.CREATED).body(rebanhoService.salvar(rebanho));
    }

    @GetMapping
    public ResponseEntity<List<RebanhoEntity>> rebanho(){
        List<RebanhoEntity> rebanho = rebanhoService.listar();
        return ResponseEntity.ok(rebanho);
    }

    @DeleteMapping("/{idRebanho}")
    public ResponseEntity<RebanhoEntity> excluir(@PathVariable Long idRebanho) throws Exception {
        rebanhoService.excluir(idRebanho);
        return ResponseEntity.noContent().build();
    }
}
