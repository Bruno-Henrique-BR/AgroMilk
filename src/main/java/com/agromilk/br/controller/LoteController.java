package com.agromilk.br.controller;

import com.agromilk.br.dto.LoteDTO;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.service.LoteService;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("agromilk/lote")
public class LoteController {

    @Autowired
    private LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PostMapping
    public ResponseEntity<LoteEntity> cadastrarLote(
            @RequestBody @Valid LoteRequestDTO dto)
            throws NotFoundException, BadRequestException {
        LoteEntity response = loteService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }

    @PutMapping("/{idLote}")
    public ResponseEntity<LoteEntity> atualizarLote(@PathVariable Long idLote,
                                                                          @RequestBody @Valid LoteRequestDTO dto)
            throws Exception {
        LoteEntity loteDTO = this.loteService.atualizar(dto, idLote);
        return new ResponseEntity<>(loteDTO, OK);
    }

    @GetMapping
    public ResponseEntity<Page<LoteEntity>> listarTodos(
            @RequestParam(required = false) Long idLote,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String sexo,

            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Paginacao.DEFAULT_PAGE_SIZE) Pageable pageable) throws Exception {

        Page<LoteEntity> response = loteService.listar(
                idLote,
                nome,
                descricao,
                sexo,
                pageable);
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/{idLote}")
    public ResponseEntity<LoteEntity> excluir(@PathVariable Long idLote) throws Exception {
        loteService.excluir(idLote);
        return ResponseEntity.noContent().build();
    }
}
