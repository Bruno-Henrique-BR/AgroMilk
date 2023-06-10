package com.agromilk.br.service;


import com.agromilk.br.constants.*;
import com.agromilk.br.dto.*;
import com.agromilk.br.entity.*;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.FuncionarioRepository;
import com.agromilk.br.repository.OrdenhaRepository;
import com.agromilk.br.repository.TanqueRepository;
import com.agromilk.br.request.OrdenhaRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
@Transactional
public class OrdenhaServiceImpl implements OrdenhaService {

    @Autowired
    private OrdenhaRepository ordenhaRepository;

    private AnimalRepository animalRepository;

    private FuncionarioRepository funcionarioRepository;

    private TanqueRepository tanqueRepository;

    public OrdenhaServiceImpl(OrdenhaRepository ordenhaRepository, AnimalRepository animalRepository, FuncionarioRepository funcionarioRepository, TanqueRepository tanqueRepository) {
        this.ordenhaRepository = ordenhaRepository;
        this.animalRepository = animalRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.tanqueRepository = tanqueRepository;
    }


    @Override
    public void excluir(Long idOrdenha) throws Exception {
        ordenhaRepository.deleteById(idOrdenha);
    }

    @Override
    public List<OrdenhaEntity> listar(
            Long idOrdenha,
            LocalDate data,
            Double primeiraOrdenha,
            Double segundaOrdenha,
            Long idAnimal,
            Long idTanque,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<OrdenhaEntity> lista = ordenhaRepository.findByFilter(
                idOrdenha,
                data,
                primeiraOrdenha,
                segundaOrdenha,
                idAnimal,
                idTanque,
                pageable);

        return lista;
    }

    private OrdenhaEntity saveOrdenha(OrdenhaRequestDTO dto)
            throws Exception {
        Optional<AnimalEntity> animal = animalRepository
                .findById(dto.getIdAnimal());
        if (!animal.isPresent()) {
            throw new NotFoundException(AnimalConstants.IDANIMAL_NOTFOUND);
        }

        Optional<TanqueEntity> tanque = tanqueRepository
                .findById(dto.getIdTanque());
        if (!tanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        OrdenhaEntity saveOrdenha;
        if(nonNull(dto.getIdOrdenha())) {
            Optional<OrdenhaEntity> optionalOrdenha = ordenhaRepository.findById(dto.getIdOrdenha());
            if (!optionalOrdenha.isPresent()) {
                throw new NotFoundException(OrdenhaConstants.IDORDENHA_NOTFOUND);
            }
            saveOrdenha = optionalOrdenha.get();
        }
        else {
            saveOrdenha = new OrdenhaEntity();
        }
        saveOrdenha.setData(dto.getData());
        saveOrdenha.setPrimeiraOrdenha(dto.getPrimeiraOrdenha());
        saveOrdenha.setSegundaOrdenha(dto.getSegundaOrdenha());
        saveOrdenha.setAnimal(animal.get());
        saveOrdenha.setTanque(tanque.get());
        this.validate(dto);
        saveOrdenha = ordenhaRepository.save(saveOrdenha);
        return saveOrdenha;

    }

    private void validate(OrdenhaRequestDTO dto) throws Exception {
        Optional<TanqueEntity> tanque = tanqueRepository.findById(dto.getIdTanque());
        if (!tanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        if (dto.getPrimeiraOrdenha() < 0 || dto.getSegundaOrdenha() < 0) {
            throw new Exception(TanqueConstants.TANQUE_VALOR_INVALID);
        }
        Double soma = dto.getPrimeiraOrdenha() + dto.getSegundaOrdenha() + tanque.get().getQuantidadeAtual();
        Double capacidadeTanque = tanque.get().getCapacidade();
        if (soma <= capacidadeTanque) {
            tanqueRepository.enviarLeiteTanque(tanque.get().getIdTanque(), dto.getPrimeiraOrdenha() + dto.getSegundaOrdenha());
        } else {
            throw new Exception(TanqueConstants.TANQUE_FULL);
        }
    }


    public OrdenhaEntity salvar(OrdenhaRequestDTO dto)
            throws Exception {

        if (nonNull(dto.getIdOrdenha())) {
            throw new NotFoundException(OrdenhaConstants.IDORDENHA_INSERT);
        }
        return saveOrdenha(dto);
    }


    public OrdenhaEntity salvarOrdenha(OrdenhaRequestDTO dto) throws Exception {
        Optional<AnimalEntity> animal = animalRepository.findById(dto.getIdAnimal());
        if (!animal.isPresent()) {
            throw new NotFoundException(AnimalConstants.IDANIMAL_NOTFOUND);
        }

        Optional<TanqueEntity> tanque = tanqueRepository.findById(dto.getIdTanque());
        if (!tanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }

        OrdenhaEntity saveOrdenha;
        if (dto.getIdOrdenha() != null) {
            Optional<OrdenhaEntity> optionalOrdenha = ordenhaRepository.findById(dto.getIdOrdenha());
            if (!optionalOrdenha.isPresent()) {
                throw new NotFoundException(OrdenhaConstants.IDORDENHA_NOTFOUND);
            }
            saveOrdenha = optionalOrdenha.get();
        } else {
            saveOrdenha = new OrdenhaEntity();
        }

        LocalDate dataOrdenha;
        if (dto.getData() == null) {
            dataOrdenha = LocalDate.now();
        } else {
            dataOrdenha = dto.getData();
        }

        saveOrdenha.setData(dataOrdenha);
        saveOrdenha.setPrimeiraOrdenha(dto.getPrimeiraOrdenha());
        saveOrdenha.setSegundaOrdenha(dto.getSegundaOrdenha());
        saveOrdenha.setAnimal(animal.get());
        saveOrdenha.setTanque(tanque.get());

        validate(dto);

        saveOrdenha = ordenhaRepository.save(saveOrdenha);
        return saveOrdenha;
    }




    public OrdenhaEntity atualizar(OrdenhaRequestDTO dto, Long idOrdenha) throws Exception {
        if (isNull(idOrdenha)) {
            throw new BadRequestException(OrdenhaConstants.IDORDENHA_NOTFOUND);
        }
        dto.setIdOrdenha(idOrdenha);

        return saveOrdenha(dto);
    }

    public OrdenhaEntity findById(Long idOrdenha) throws javassist.tools.rmi.ObjectNotFoundException {
        Optional<OrdenhaEntity> obj = ordenhaRepository.findById(idOrdenha);
        return obj.orElseThrow(() -> new javassist.tools.rmi.ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idOrdenha + ", Tipo: " + OrdenhaEntity.class.getName()));
    }

    public List<ProducaoLeiteMensalDTO> obterGraficoProducaoLeite() {
        List<ProducaoLeiteMensalDTO> graficoProducaoLeite = new ArrayList<>();

        // Realize a consulta no banco de dados para obter a soma da produção de leite de todos os animais por mês
        List<Object[]> resultadoConsulta = ordenhaRepository.obterSomaProducaoLeitePorMes();

        // Mapeie o resultado da consulta para a lista de DTOs do gráfico de produção de leite
        for (Object[] resultado : resultadoConsulta) {
            String mes = (String) resultado[0];
            Double producaoLeite = (Double) resultado[1];

            ProducaoLeiteMensalDTO dto = new ProducaoLeiteMensalDTO(mes, producaoLeite);
            graficoProducaoLeite.add(dto);
        }

        return graficoProducaoLeite;
    }


    public List<ProducaoLeiteSemanalDTO> obterGraficoProducaoLeitePorSemana() {
        List<ProducaoLeiteSemanalDTO> graficoProducaoLeiteSemana = new ArrayList<>();

        // Realize a consulta no banco de dados para obter a soma da produção de leite de todos os animais por mês
        List<Object[]> resultadoConsulta = ordenhaRepository.obterSomaProducaoLeitePorSemana();

        // Mapeie o resultado da consulta para a lista de DTOs do gráfico de produção de leite
        for (Object[] resultado : resultadoConsulta) {
            String semana = (String) resultado[0];
            Double producaoLeite = (Double) resultado[1];

            ProducaoLeiteSemanalDTO dto = new ProducaoLeiteSemanalDTO(semana, producaoLeite);
            graficoProducaoLeiteSemana.add(dto);
        }

        return graficoProducaoLeiteSemana;
    }

    public List<ProducaoLeiteDiariaDTO> obterSomaProducaoLeiteUltimos7Dias() {
        List<ProducaoLeiteDiariaDTO> graficoProducaoLeiteDiaria = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 7); // Limita a 7 registros
        List<Object[]> resultadoConsulta = ordenhaRepository.obterSomaProducaoLeiteUltimosSeteDias(pageable);

        for (Object[] resultado : resultadoConsulta) {
            Integer dataDia = (Integer) resultado[0];
            Long quantidadeOrdenhas = (Long) resultado[1];
            Double somaProducaoLeite = (Double) resultado[2];


            ProducaoLeiteDiariaDTO dto = new ProducaoLeiteDiariaDTO(dataDia.toString(), quantidadeOrdenhas, somaProducaoLeite);
            graficoProducaoLeiteDiaria.add(dto);
        }
        return graficoProducaoLeiteDiaria;
    }

    public List<ProducaoLeiteDiariaDTO> obterSomaProducaoLeiteUltimos7DiasAnimal(Long idAnimal) {
        List<ProducaoLeiteDiariaDTO> graficoProducaoLeiteDiaria = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 7); // Limita a 7 registros
        List<Object[]> resultadoConsulta = ordenhaRepository.obterSomaProducaoLeiteUltimosSeteDiasAnimal(pageable, idAnimal);

        for (Object[] resultado : resultadoConsulta) {
            Integer dataDia = (Integer) resultado[0];
            Long quantidadeOrdenhas = (Long) resultado[1];
            Double somaProducaoLeite = (Double) resultado[2];

            ProducaoLeiteDiariaDTO dto = new ProducaoLeiteDiariaDTO(dataDia.toString(), quantidadeOrdenhas, somaProducaoLeite);
            graficoProducaoLeiteDiaria.add(dto);
        }
        return graficoProducaoLeiteDiaria;
    }


    public List<ProducaoLeiteMensalDTO> obterGraficoProducaoLeiteAnimal(Long idAnimal) {
        List<ProducaoLeiteMensalDTO> graficoProducaoLeite = new ArrayList<>();

        // Realize a consulta no banco de dados para obter a soma da produção de leite de todos os animais por mês
        List<Object[]> resultadoConsulta = ordenhaRepository.obterSomaProducaoLeitePorMesAnimal(idAnimal);

        // Mapeie o resultado da consulta para a lista de DTOs do gráfico de produção de leite
        for (Object[] resultado : resultadoConsulta) {
            String mes = (String) resultado[0];
            Double producaoLeite = (Double) resultado[1];

            ProducaoLeiteMensalDTO dto = new ProducaoLeiteMensalDTO(mes, producaoLeite);
            graficoProducaoLeite.add(dto);
        }

        return graficoProducaoLeite;
    }

    public List<ProducaoLeiteSemanalDTO> obterGraficoProducaoLeiteSemanalAnimal(Long idAnimal) {
        List<ProducaoLeiteSemanalDTO> graficoProducaoLeite = new ArrayList<>();

        // Realize a consulta no banco de dados para obter a soma da produção de leite de todos os animais por mês
        List<Object[]> resultadoConsulta = ordenhaRepository.obterSomaProducaoLeitePorSemanaAnimal(idAnimal);

        // Mapeie o resultado da consulta para a lista de DTOs do gráfico de produção de leite
        for (Object[] resultado : resultadoConsulta) {
            String semana = (String) resultado[0];
            Double producaoLeite = (Double) resultado[1];

            ProducaoLeiteSemanalDTO dto = new ProducaoLeiteSemanalDTO(semana, producaoLeite);
            graficoProducaoLeite.add(dto);
        }

        return graficoProducaoLeite;
    }
    public List<TaxaOcupacaoTanqueDTO> obterGraficoTaxaOcupacaoTanques() {
        List<TanqueEntity> tanques = tanqueRepository.findAll(); // Supondo que você tenha um repositório para acessar os tanques

        List<TaxaOcupacaoTanqueDTO> graficoTaxaOcupacaoTanques = new ArrayList<>();

        for (TanqueEntity tanque : tanques) {
            Double taxaOcupacao = calcularTaxaOcupacaoTanque(tanque); // Método para calcular a taxa de ocupação de um tanque específico

            TaxaOcupacaoTanqueDTO taxaOcupacaoTanqueDTO = new TaxaOcupacaoTanqueDTO(tanque.getIdTanque(), tanque.getModelo(), taxaOcupacao);
            graficoTaxaOcupacaoTanques.add(taxaOcupacaoTanqueDTO);
        }

        return graficoTaxaOcupacaoTanques;
    }
    private double calcularTaxaOcupacaoTanque(TanqueEntity tanque) {

        double capacidadeTanque = tanque.getCapacidade();
        double quantidadeLeiteArmazenada = tanque.getQuantidadeAtual();

        if (capacidadeTanque == 0) {
            return 0;
        }

        return quantidadeLeiteArmazenada / capacidadeTanque * 100;
    }
    public List<ProducaoLeiteDiariaDTO> obterProducaoLeitePorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        List<ProducaoLeiteDiariaDTO> producaoLeite = new ArrayList<>();

        List<Object[]> ordenhas = ordenhaRepository.obterOrdenhasPorPeriodo(dataInicial, dataFinal);
        for (Object[] resultado : ordenhas) {
            Integer dataDia = (Integer) resultado[0];
            Long quantidadeOrdenhas = (Long) resultado[1];
            Double somaProducaoLeite = (Double) resultado[2];

            // Converter dataDia para String no formato desejado
            String dataFormatada = formatDate(dataDia);

            ProducaoLeiteDiariaDTO producaoDiaria = new ProducaoLeiteDiariaDTO(dataFormatada, quantidadeOrdenhas, somaProducaoLeite);
            producaoLeite.add(producaoDiaria);
        }

        return producaoLeite;
    }



    private String formatDate(Integer dataDia) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        data.setDate(dataDia);
        return format.format(data);
    }






}
