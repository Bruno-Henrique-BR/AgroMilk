package com.agromilk.br.controller;

import com.agromilk.br.dto.MovimentoDTO;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.service.AnimalService;
import com.agromilk.br.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"${allowed.origin}"})
@RestController
@RequestMapping("agromilk/movimento")
public class MovimentoController {

    @Autowired
    private MovimentoService movimentoService;


    public MovimentoController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }



    @GetMapping("/{idAnimal}")
    public List<MovimentoDTO> listarMovimento(@PathVariable Long idAnimal) {
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(idAnimal);
        return movimentoService.getMovimentosByAnimal(idAnimal);
    }
    @GetMapping("/media-dias-lactacao")
    public Integer calcularMediaDiasLactacao() {
        return movimentoService.calcularMediaDiasLactacao();
    }

    @GetMapping("/media-dias-gestacao")
    public Integer calcularMediaDiasGestacao() {
        return movimentoService.calcularMediaDiasGestacao();
    }
    @GetMapping("/media-dias-secas")
    public Integer calcularMediaDiasSecas() {
        return movimentoService.calcularMediaDiasSecas();
    }
}
