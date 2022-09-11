package com.agromilk.br.controller;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.request.MarcaRequestDTO;
import com.agromilk.br.service.MarcaService;
import com.agromilk.br.service.TanqueService;
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
@RequestMapping("agromilk/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping
    public ResponseEntity<MarcaEntity> cadastrarMarca(
            @RequestBody @Valid MarcaRequestDTO dto)
            throws NotFoundException, BadRequestException {
        MarcaEntity response = marcaService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }

    @PutMapping("/{idMarca}")
    public ResponseEntity<MarcaEntity> atualizarMarca(@PathVariable Long idMarca,
                                                    @RequestBody @Valid MarcaRequestDTO dto)
            throws Exception {
        MarcaEntity marcaDTO = this.marcaService.atualizar(dto, idMarca);
        return new ResponseEntity<>(marcaDTO, OK);
    }



    @DeleteMapping("/{idMarca}")
    public ResponseEntity<MarcaEntity> excluirMarca(@PathVariable Long idMarca) throws Exception {
        marcaService.excluir(idMarca);
        return ResponseEntity.noContent().build();
    }
}
