package com.agromilk.br.controller;

import com.agromilk.br.dto.AnimalDTO;
import com.agromilk.br.dto.LaticinioDTO;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.LaticinioRequestDTO;
import com.agromilk.br.service.LaticinioService;
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

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = {"${allowed.origin}"})
@RestController
@RequestMapping("agromilk/laticinio")
public class LaticinioController {
    @Autowired
    private LaticinioService laticinioService;

    public LaticinioController(LaticinioService laticinioService) {
        this.laticinioService = laticinioService;
    }

    @PostMapping
    public ResponseEntity<LaticinioEntity> cadastrarLaticinio(
            @RequestBody @Valid LaticinioRequestDTO dto)
            throws NotFoundException, BadRequestException {
        LaticinioEntity response = laticinioService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }
    @GetMapping(value = "/{idLaticinio}")
    public ResponseEntity<LaticinioDTO> findById(@PathVariable Long idLaticinio) throws ObjectNotFoundException {
        LaticinioEntity obj = laticinioService.findById(idLaticinio);
        return ResponseEntity.ok().body(new LaticinioDTO(obj));
    }
    @PutMapping("/{idLaticinio}")
    public ResponseEntity<LaticinioEntity> atualizarLaticinio(@PathVariable Long idLaticinio,
                                                                  @RequestBody @Valid LaticinioRequestDTO dto)
            throws Exception {
        LaticinioEntity laticinioDTO = this.laticinioService.atualizar(dto, idLaticinio);
        return new ResponseEntity<>(laticinioDTO, OK);
    }

    @GetMapping
    public ResponseEntity<List<LaticinioEntity>> listarTodos(
            @RequestParam(required = false) Long idLaticinio,
            @RequestParam(required = false) String razaoSocial,
            @RequestParam(required = false) String cnpj,
            @RequestParam(required = false) String endereco,
            @RequestParam(required = false) String telefone,

            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Integer.MAX_VALUE) Pageable pageable) throws Exception {

        List<LaticinioEntity> response = laticinioService.listar(
                idLaticinio,
                razaoSocial,
                cnpj,
                endereco,
                telefone,
                pageable);
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/{idLaticinio}")
    public ResponseEntity<LaticinioEntity> excluir(@PathVariable Long idLaticinio) throws Exception {
        laticinioService.excluir(idLaticinio);
        return ResponseEntity.noContent().build();
    }
}