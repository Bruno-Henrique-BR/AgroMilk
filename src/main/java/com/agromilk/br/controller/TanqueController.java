package com.agromilk.br.controller;

import com.agromilk.br.dto.RacaDTO;
import com.agromilk.br.dto.TanqueDTO;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.TanqueRequestDTO;
import com.agromilk.br.service.TanqueService;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
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
@CrossOrigin(   origins = "http://localhost:4200")

@RestController
@RequestMapping("agromilk/tanque")
public class TanqueController {

    @Autowired
    private TanqueService tanqueService;

    public TanqueController(TanqueService tanqueService) {
        this.tanqueService = tanqueService;
    }
    @PostMapping
    public ResponseEntity<TanqueEntity> cadastrarTanque(
            @RequestBody @Valid TanqueRequestDTO dto)
            throws NotFoundException, BadRequestException {
        TanqueEntity response = tanqueService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }
    @GetMapping(value = "/{idTanque}")
    public ResponseEntity<TanqueDTO> findById(@PathVariable Long idTanque) throws ObjectNotFoundException {
        TanqueEntity obj = tanqueService.findById(idTanque);
        return ResponseEntity.ok().body(new TanqueDTO(obj));
    }
    @PutMapping("/{idTanque}")
    public ResponseEntity<TanqueEntity> atualizarTanque(@PathVariable Long idTanque,
                                                    @RequestBody @Valid TanqueRequestDTO dto)
            throws Exception {
        TanqueEntity tanqueDTO = this.tanqueService.atualizar(dto, idTanque);
        return new ResponseEntity<>(tanqueDTO, OK);
    }

    @GetMapping
    public ResponseEntity<List<TanqueEntity>> listarTodos(
            @RequestParam(required = false) Long idTanque,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Double capacidade,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) LocalDate dataFabricacao,
            @RequestParam(required = false) Boolean ativo,

            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Paginacao.DEFAULT_PAGE_SIZE) Pageable pageable) throws Exception {

        List<TanqueEntity> response = tanqueService.listar(
                idTanque,
                descricao,
                capacidade,
                modelo,
                dataFabricacao,
                ativo,
                pageable);
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/{idTanque}")
    public ResponseEntity<TanqueEntity> excluir(@PathVariable Long idTanque) throws Exception {
        tanqueService.excluir(idTanque);
        return ResponseEntity.noContent().build();
    }
}
