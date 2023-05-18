package com.agromilk.br.controller;

import com.agromilk.br.dto.AnimalDTO;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.AnimalRequestDTO;
import com.agromilk.br.service.AnimalService;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = {"${allowed.origin}"})
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
    @GetMapping(value = "/{idAnimal}")
    public ResponseEntity<AnimalDTO> findById(@PathVariable Long idAnimal) throws ObjectNotFoundException {
        AnimalEntity obj = animalService.findById(idAnimal);
        return ResponseEntity.ok().body(new AnimalDTO(obj));
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<AnimalEntity>> pesquisar(@RequestParam String apelido) throws Exception {
        List<AnimalEntity> animais = animalService.pesquisar(apelido);
        return new ResponseEntity<>(animais, HttpStatus.OK);
    }


    @GetMapping(value = "/lote/{idLote}")
    public ResponseEntity<List<AnimalDTO>> findByIdLote(@PathVariable Long idLote) {
        List<AnimalEntity> list = animalService.findByIdLote(idLote);
        List<AnimalDTO> listDTO = list.stream().map(AnimalDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping
    public ResponseEntity<List<AnimalEntity>> listarTodos(
            @RequestParam(required = false) Long idAnimal,
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String apelido,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(required = false) LocalDate dataCompra,
            @RequestParam(required = false) String nomeLote,
            @RequestParam(required = false) String nomeRaca,
            @RequestParam(required = false) Double media,



            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Integer.MAX_VALUE) Pageable pageable) throws Exception {

        List<AnimalEntity> response = animalService.listar(
                idAnimal,
                codigo,
                apelido,
                dataNascimento,
                dataCompra,
                nomeLote,
                nomeRaca,
                media,
                pageable);
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("/lactantes")
    public ResponseEntity<List<AnimalEntity>> listarAnimaisLactantes() {
        List<AnimalEntity> animais = animalService.listarAnimaisLactantes();
        return ResponseEntity.ok().body(animais);
    }

    @GetMapping("/lote/{idLote}/nao-contem")
    public ResponseEntity<List<AnimalDTO>> findAnimaisNaoContemNoLote(@PathVariable Long idLote) {
        List<AnimalEntity> list = animalService.findAnimaisNaoContemNoLote(idLote);
        List<AnimalDTO> listDTO = list.stream().map(AnimalDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @DeleteMapping("/{idAnimal}")
    public ResponseEntity<AnimalEntity> excluir(@PathVariable Long idAnimal) throws Exception {
        animalService.excluir(idAnimal);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/lote/{idLote}/animais")
    public ResponseEntity<List<AnimalEntity>> listarPorIdLote(@PathVariable Long idLote) {
        List<AnimalEntity> animais = animalService.listarPorIdLote(idLote);
        return ResponseEntity.ok().body(animais);
    }
    @GetMapping(value = "/raca/{idRaca}/animais")
    public ResponseEntity<List<AnimalEntity>> listarPorIdRaca(@PathVariable Long idRaca) {
        List<AnimalEntity> animais = animalService.listarPorIdRaca(idRaca);
        return ResponseEntity.ok().body(animais);
    }

    @GetMapping("/qtsAnimal")
    public Long qtsAnimal(){
        return animalService.animais();
    }

    @GetMapping("/qtsAnimalLactacao")
    public Long qtsAnimalLactacao(){
        return animalService.animaisLactacao();
    }
    @GetMapping("/qtsAnimalSeca")
    public Long qtsAnimalSeca(){
        return animalService.animaisSeca();
    }

    @GetMapping("/mediaLitro")
    public Double mediaLitro(){
        return animalService.animaisMediaLitro();
    }

    @GetMapping("/porcentagemLactantes")
    public Double porcentagemLactantes(){
        return animalService.porcentagemLactantes();
    }

    @GetMapping("/porcentagemSecas")
    public Double porcentagemSecas(){
        return animalService.porcentagemSecas();
    }

    @GetMapping("/melhores")
    public ResponseEntity<List<AnimalEntity>> getMelhoresVacas() {
        List<AnimalEntity> melhoresVacas = animalService.getMelhoresVacas();
        return ResponseEntity.ok(melhoresVacas);
    }

    @GetMapping("/piores")
    public ResponseEntity<List<AnimalEntity>> getPioresVacas() {
        List<AnimalEntity> pioresVacas = animalService.getPioresVacas();
        return ResponseEntity.ok(pioresVacas);
    }
}
