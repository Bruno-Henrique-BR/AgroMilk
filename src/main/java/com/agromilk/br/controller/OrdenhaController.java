package com.agromilk.br.controller;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.AnimalRequestDTO;
import com.agromilk.br.request.OrdenhaRequestDTO;
import com.agromilk.br.service.OrdenhaService;
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
@RequestMapping("agromilk/ordenha")
public class OrdenhaController {

    @Autowired
    private OrdenhaService ordenhaService;

    public OrdenhaController(OrdenhaService ordenhaService) {
        this.ordenhaService = ordenhaService;
    }

    @PostMapping
    public ResponseEntity<OrdenhaEntity> cadastrarOrdenha(
            @RequestBody @Valid OrdenhaRequestDTO dto)
            throws NotFoundException, BadRequestException {
        OrdenhaEntity response = ordenhaService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }

    @PutMapping("/{idOrdenha}")
    public ResponseEntity<OrdenhaEntity> atualizarOrdenha(@PathVariable Long idOrdenha,
                                                        @RequestBody @Valid OrdenhaRequestDTO dto)
            throws Exception {
        OrdenhaEntity ordenhaDTO = this.ordenhaService.atualizar(dto, idOrdenha);
        return new ResponseEntity<>(ordenhaDTO, OK);
    }


    @DeleteMapping("/{idOrdenha}")
    public ResponseEntity<OrdenhaEntity> excluir(@PathVariable Long idOrdenha) throws Exception {
        ordenhaService.excluir(idOrdenha);
        return ResponseEntity.noContent().build();
    }
}
