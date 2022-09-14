package com.agromilk.br.controller;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.RacaRequestDTO;
import com.agromilk.br.service.RacaService;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("agromilk/raca")
public class RacaController {

    @Autowired
    private RacaService racaService;

    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    @PostMapping
    public ResponseEntity<RacaEntity> cadastrarRaca(
            @RequestBody @Valid RacaRequestDTO dto)
            throws NotFoundException, BadRequestException {
        RacaEntity response = racaService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }

    @PutMapping("/{idRaca}")
    public ResponseEntity<RacaEntity> atualizarRaca(@PathVariable Long idRaca,
                                                    @RequestBody @Valid RacaRequestDTO dto)
            throws Exception {
        RacaEntity racaDTO = this.racaService.atualizar(dto, idRaca);
        return new ResponseEntity<>(racaDTO, OK);
    }

    @GetMapping
    public ResponseEntity<Page<RacaEntity>> listarTodos(
            @RequestParam(required = false) Long idRaca,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String descricao,

            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Paginacao.DEFAULT_PAGE_SIZE) Pageable pageable) throws Exception {

        Page<RacaEntity> response = racaService.listar(
                idRaca,
                nome,
                descricao,
                pageable);
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/{idRaca}")
    public ResponseEntity<RacaEntity> excluir(@PathVariable Long idRaca) throws Exception {
        racaService.excluir(idRaca);
        return ResponseEntity.noContent().build();
    }

}
