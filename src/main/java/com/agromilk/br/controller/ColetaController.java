package com.agromilk.br.controller;

import com.agromilk.br.dto.AnimalDTO;
import com.agromilk.br.dto.ColetaDTO;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.ColetaEntity;
import com.agromilk.br.request.ColetaRequestDTO;
import com.agromilk.br.service.ColetaService;
import com.agromilk.br.util.Paginacao;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = {"${allowed.origin}"})
@RestController
@RequestMapping("agromilk/coleta")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    public ColetaController(ColetaService coletaService) {
        this.coletaService = coletaService;
    }


    @PostMapping
    public ResponseEntity<ColetaEntity> cadastrarColeta(
            @RequestBody @Valid ColetaRequestDTO dto)
            throws Exception {
        ColetaEntity response = coletaService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }

    @PutMapping("/{idColeta}")
    public ResponseEntity<ColetaEntity> atualizarColeta(@PathVariable Long idColeta,
                                                        @RequestBody @Valid ColetaRequestDTO dto)
            throws Exception {
        ColetaEntity coletaDTO = this.coletaService.atualizar(dto, idColeta);
        return new ResponseEntity<>(coletaDTO, OK);
    }
    @GetMapping(value = "/{idColeta}")
    public ResponseEntity<ColetaDTO> findById(@PathVariable Long idColeta) throws ObjectNotFoundException {
        ColetaEntity obj = coletaService.findById(idColeta);
        return ResponseEntity.ok().body(new ColetaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ColetaEntity>> listarTodos(
            @RequestParam(required = false) Long idColeta,
            @RequestParam(required = false) Long idTanque,
            @RequestParam(required = false) Long idLaticinio,
            @RequestParam(required = false) Double quantidade,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) LocalDate data,



            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Paginacao.DEFAULT_PAGE_SIZE) Pageable pageable) throws Exception {

        List<ColetaEntity> response = coletaService.listar(
                idColeta,
                idTanque,
                idLaticinio,
                quantidade,
                descricao,
                data,
                pageable);
        return new ResponseEntity<>(response, OK);
    }
    @DeleteMapping("/{idColeta}")
    public ResponseEntity<ColetaEntity> excluir(@PathVariable Long idColeta) throws Exception {
        coletaService.excluir(idColeta);
        return ResponseEntity.noContent().build();
    }
}
