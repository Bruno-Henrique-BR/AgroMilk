package com.agromilk.br.controller;

import com.agromilk.br.dto.*;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.request.OrdenhaRequestDTO;
import com.agromilk.br.service.OrdenhaService;
import com.agromilk.br.util.Paginacao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public ResponseEntity<?> cadastrarOrdenha(@RequestBody Object requestBody) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<OrdenhaRequestDTO> ordenhas = new ArrayList<>();

        if (requestBody instanceof List) {
            // Se o requestBody for uma lista, convertemos diretamente para uma lista de OrdenhaRequestDTO
            ordenhas = objectMapper.convertValue(requestBody, new TypeReference<List<OrdenhaRequestDTO>>() {});
        } else {
            // Se não for uma lista, assumimos que é um único objeto e convertemos para OrdenhaRequestDTO
            OrdenhaRequestDTO dto = objectMapper.convertValue(requestBody, OrdenhaRequestDTO.class);
            ordenhas.add(dto);
        }

        // Aqui você pode tratar e salvar as ordenhas no banco de dados
        List<OrdenhaEntity> responses = new ArrayList<>();
        for (OrdenhaRequestDTO dto : ordenhas) {
            OrdenhaEntity response = ordenhaService.salvarOrdenha(dto);
            responses.add(response);
        }

        return new ResponseEntity<>(responses, HttpStatus.CREATED);
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
    @GetMapping("/grafico-taxa-ocupacao-tanques")
    public ResponseEntity<List<TaxaOcupacaoTanqueDTO>> obterGraficoTaxaOcupacaoTanques() {
        List<TaxaOcupacaoTanqueDTO> graficoTaxaOcupacaoTanques = ordenhaService.obterGraficoTaxaOcupacaoTanques();
        return ResponseEntity.ok().body(graficoTaxaOcupacaoTanques);
    }

    @GetMapping("/grafico-producao-leite")
    public ResponseEntity<List<ProducaoLeiteMensalDTO>> obterGraficoProducaoLeite() {
        List<ProducaoLeiteMensalDTO> graficoProducaoLeite = ordenhaService.obterGraficoProducaoLeite();
        return ResponseEntity.ok().body(graficoProducaoLeite);
    }

    @GetMapping("/grafico-producao-leite-semana")
    public ResponseEntity<List<ProducaoLeiteSemanalDTO>> obterSomaProducaoLeitePorSemana() {
        List<ProducaoLeiteSemanalDTO> graficoProducaoLeitePorSemana = ordenhaService.obterGraficoProducaoLeitePorSemana();
        return ResponseEntity.ok().body(graficoProducaoLeitePorSemana);
    }

    @GetMapping("/grafico-producao-ultimos-7-dias")
    public ResponseEntity<List<ProducaoLeiteDiariaDTO>> obterProducaoLeiteUltimos7Dias() {
        List<ProducaoLeiteDiariaDTO> graficoProducaoLeiteDiaria = ordenhaService.obterSomaProducaoLeiteUltimos7Dias();
        return ResponseEntity.ok().body(graficoProducaoLeiteDiaria);
    }

    @GetMapping("/grafico-producao-ultimos-7-dias/{idAnimal}")
    public ResponseEntity<List<ProducaoLeiteDiariaDTO>> obterProducaoLeiteUltimos7DiasAnimal(@PathVariable Long idAnimal) {
        List<ProducaoLeiteDiariaDTO> graficoProducaoLeiteDiaria = ordenhaService.obterSomaProducaoLeiteUltimos7DiasAnimal(idAnimal);
        return ResponseEntity.ok().body(graficoProducaoLeiteDiaria);
    }

    @GetMapping("/grafico-producao-leite-semana/{idAnimal}")
    public ResponseEntity<List<ProducaoLeiteSemanalDTO>> obterSomaProducaoLeitePorSemanaAnimal(@PathVariable Long idAnimal) {
        List<ProducaoLeiteSemanalDTO> graficoProducaoLeitePorSemanaAnimal = ordenhaService.obterGraficoProducaoLeiteSemanalAnimal(idAnimal);
        return ResponseEntity.ok().body(graficoProducaoLeitePorSemanaAnimal);
    }

    @GetMapping("/grafico-producao-leite/{idAnimal}")
    public ResponseEntity<List<ProducaoLeiteMensalDTO>> obterGraficoProducaoLeiteAnimal(@PathVariable Long idAnimal) {
        List<ProducaoLeiteMensalDTO> graficoProducaoLeite = ordenhaService.obterGraficoProducaoLeiteAnimal(idAnimal);
        return ResponseEntity.ok().body(graficoProducaoLeite);
    }


    @GetMapping("/relatorio-producao-diaria")
    public ResponseEntity<RelatorioProducaoDiariaDTO> obterRelatorioProducaoDiaria(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicial,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFinal) {
        List<ProducaoLeiteDiariaDTO> producaoDiaria = ordenhaService.obterProducaoLeitePorPeriodo(dataInicial, dataFinal);
        double somaProducao = producaoDiaria.stream().mapToDouble(ProducaoLeiteDiariaDTO::getSomaProducaoLeite).sum();

        RelatorioProducaoDiariaDTO relatorioProducaoDiaria = new RelatorioProducaoDiariaDTO(dataInicial, dataFinal, producaoDiaria, somaProducao);
        return ResponseEntity.ok().body(relatorioProducaoDiaria);
    }


}
