package com.agromilk.br.controller;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("agromilk/lote")
public class LoteController {

    @Autowired
    private LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PostMapping
    public ResponseEntity<LoteEntity> cadastrar(@RequestBody @Valid LoteEntity lote){
        return ResponseEntity.status(HttpStatus.CREATED).body(loteService.salvar(lote));
    }

    @GetMapping
    public ResponseEntity<List<LoteEntity>> lote(){
        List<LoteEntity> lote = loteService.listar();
        return ResponseEntity.ok(lote);
    }

    @DeleteMapping("/{idLote}")
    public ResponseEntity<LoteEntity> excluir(@PathVariable Long idLote) throws Exception {
        loteService.excluir(idLote);
        return ResponseEntity.noContent().build();
    }
}
