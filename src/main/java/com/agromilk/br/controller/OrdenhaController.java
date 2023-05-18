package com.agromilk.br.controller;

import com.agromilk.br.dto.OrdenhaDTO;
import com.agromilk.br.dto.ProducaoLeiteMensalDTO;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.request.OrdenhaRequestDTO;
import com.agromilk.br.service.OrdenhaService;
import com.agromilk.br.util.Paginacao;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public ResponseEntity<OrdenhaEntity> cadastrarOrdenhaa(
            @RequestBody @Valid OrdenhaRequestDTO dto)
            throws Exception {
        OrdenhaEntity response = ordenhaService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }



    @PostMapping("/ordenhas")
    public ResponseEntity<OrdenhaEntity> cadastrarOrdenha(@RequestBody OrdenhaRequestDTO dto) throws Exception {
        // Aqui você pode tratar e salvar a ordenha no banco de dados
        OrdenhaEntity response = ordenhaService.salvarOrdenha(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
            @RequestParam(required = false) Double primeiraOrdenha,
            @RequestParam(required = false) Double segundaOrdenha,
            @RequestParam(required = false) Long idAnimal,
            @RequestParam(required = false) Long idTanque,
            @RequestParam(required = false) String nomeFuncionario,


            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Integer.MAX_VALUE) Pageable pageable) throws Exception {

        List<OrdenhaEntity> response = ordenhaService.listar(
                idOrdenha,
                data,
                primeiraOrdenha,
                segundaOrdenha,
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

    @GetMapping("/grafico-producao-leite")
    public ResponseEntity<List<ProducaoLeiteMensalDTO>> obterGraficoProducaoLeite() {
        List<ProducaoLeiteMensalDTO> graficoProducaoLeite = ordenhaService.obterGraficoProducaoLeite();
        return ResponseEntity.ok().body(graficoProducaoLeite);
    }

    @GetMapping("/grafico-producao-leite/{idAnimal}")
    public ResponseEntity<List<ProducaoLeiteMensalDTO>> obterGraficoProducaoLeiteAnimal(@PathVariable Long idAnimal) {
        List<ProducaoLeiteMensalDTO> graficoProducaoLeite = ordenhaService.obterGraficoProducaoLeiteAnimal(idAnimal);
        return ResponseEntity.ok().body(graficoProducaoLeite);
    }

}
