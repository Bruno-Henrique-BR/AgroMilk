package com.agromilk.br.controller;

import com.agromilk.br.dto.RacaDTO;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.RacaRequestDTO;
import com.agromilk.br.service.RacaService;
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
    @GetMapping(value = "/{idRaca}")
    public ResponseEntity<RacaDTO> findById(@PathVariable Long idRaca) throws ObjectNotFoundException {
        RacaEntity obj = racaService.findById(idRaca);
        return ResponseEntity.ok().body(new RacaDTO(obj));
    }

    @PutMapping("/{idRaca}")
    public ResponseEntity<RacaEntity> atualizarRaca(@PathVariable Long idRaca,
                                                    @RequestBody @Valid RacaRequestDTO dto)
            throws Exception {
        RacaEntity racaDTO = this.racaService.atualizar(dto, idRaca);
        return new ResponseEntity<>(racaDTO, OK);
    }

    @GetMapping
    public ResponseEntity<List<RacaDTO>> findAll() {
        List<RacaEntity> list = racaService.findAll();
        List<RacaDTO> listDTO = list.stream().map(obj -> new RacaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }


    @DeleteMapping("/{idRaca}")
    public ResponseEntity<RacaEntity> excluir(@PathVariable Long idRaca) throws Exception {
        racaService.excluir(idRaca);
        return ResponseEntity.noContent().build();
    }

}
