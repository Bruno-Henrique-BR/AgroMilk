package com.agromilk.br.controller;

import com.agromilk.br.dto.OrdenhaDTO;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.request.OrdenhaRequestDTO;
import com.agromilk.br.service.OrdenhaService;
import com.agromilk.br.util.Paginacao;
import org.hibernate.ObjectNotFoundException;
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
            throws Exception {
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
    @GetMapping(value = "/{idOrdenha}")
    public ResponseEntity<OrdenhaDTO> findById(@PathVariable Long idOrdenha) throws ObjectNotFoundException, javassist.tools.rmi.ObjectNotFoundException {
        OrdenhaEntity obj = ordenhaService.findById(idOrdenha);
        return ResponseEntity.ok().body(new OrdenhaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<OrdenhaEntity>> listarTodos(
            @RequestParam(required = false) Long idOrdenha,
            @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) Long quantidade,
            @RequestParam(required = false) Long idAnimal,
            @RequestParam(required = false) Long idTanque,
            @RequestParam(required = false) String nomeFuncionario,


            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Paginacao.DEFAULT_PAGE_SIZE) Pageable pageable) throws Exception {

        List<OrdenhaEntity> response = ordenhaService.listar(
                idOrdenha,
                data,
                quantidade,
                idAnimal,
                idTanque,
                pageable);
        return new ResponseEntity<>(response, OK);
    }
    @DeleteMapping("/{idOrdenha}")
    public ResponseEntity<OrdenhaEntity> excluir(@PathVariable Long idOrdenha) throws Exception {
        ordenhaService.excluir(idOrdenha);
        return ResponseEntity.noContent().build();
    }
}
