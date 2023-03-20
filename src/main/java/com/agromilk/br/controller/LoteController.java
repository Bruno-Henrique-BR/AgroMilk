package com.agromilk.br.controller;

import com.agromilk.br.dto.LoteDTO;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.service.LoteService;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = "http://localhost:4200")

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

    @GetMapping(value = "/{idLote}")
    public ResponseEntity<LoteDTO> findById(@PathVariable Long idLote) throws ObjectNotFoundException {
        LoteEntity obj = loteService.findById(idLote);
        return ResponseEntity.ok().body(new LoteDTO(obj));
    }

    @PutMapping("/{idLote}")
    public ResponseEntity<LoteEntity> atualizarLote(@PathVariable Long idLote,
                                                                          @RequestBody @Valid LoteRequestDTO dto)
            throws Exception {
        LoteEntity loteDTO = this.loteService.atualizar(dto, idLote);
        return new ResponseEntity<>(loteDTO, OK);
    }

    @GetMapping
    public ResponseEntity<List<LoteDTO>> findAll() {
        List<LoteEntity> list = loteService.findAll();
        List<LoteDTO> listDTO = list.stream().map(obj -> new LoteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @DeleteMapping("/{idLote}")
    public ResponseEntity<LoteEntity> excluir(@PathVariable Long idLote) throws Exception {
        loteService.excluir(idLote);
        return ResponseEntity.noContent().build();
    }

}
