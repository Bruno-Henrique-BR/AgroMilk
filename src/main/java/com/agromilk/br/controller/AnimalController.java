package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.AnimalRequestDTO;
import com.agromilk.br.service.AnimalService;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("agromilk/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalEntity> cadastrarAnimal(
            @RequestBody @Valid AnimalRequestDTO dto)
            throws NotFoundException, BadRequestException {
        AnimalEntity response = animalService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }

    @PutMapping("/{idAnimal}")
    public ResponseEntity<AnimalEntity> atualizarAnimal(@PathVariable Long idAnimal,
                                                    @RequestBody @Valid AnimalRequestDTO dto)
            throws Exception {
        AnimalEntity animalDTO = this.animalService.atualizar(dto, idAnimal);
        return new ResponseEntity<>(animalDTO, OK);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalEntity>> listarTodos(
            @RequestParam(required = false) Long idAnimal,
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String apelido,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(required = false) LocalDate dataCompra,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) String nomeLote,
            @RequestParam(required = false) String nomeRaca,
            @RequestParam(required = false) Boolean lactacao,


            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Paginacao.DEFAULT_PAGE_SIZE) Pageable pageable) throws Exception {

        Page<AnimalEntity> response = animalService.listar(
                idAnimal,
                codigo,
                apelido,
                dataNascimento,
                dataCompra,
                cor,
                nomeLote,
                nomeRaca,
                lactacao,
                pageable);
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/{idAnimal}")
    public ResponseEntity<AnimalEntity> excluir(@PathVariable Long idAnimal) throws Exception {
        animalService.excluir(idAnimal);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/qtsAnimal")
    public Long qtsAnimal(){
        return animalService.animais();
    }

    @GetMapping("/qtsAnimalLactacao")
    public Long qtsAnimalLactacao(){
        return animalService.animaisLactacao();
    }

    @GetMapping("/mediaLitro")
    public Double mediaLitro(){
        return animalService.animaisMediaLitro();
    }
}
