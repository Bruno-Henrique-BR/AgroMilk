package com.agromilk.br.service;


import com.agromilk.br.dto.ProducaoLeiteDiariaDTO;
import com.agromilk.br.dto.ProducaoLeiteMensalDTO;
import com.agromilk.br.dto.ProducaoLeiteSemanalDTO;
import com.agromilk.br.dto.TaxaOcupacaoTanqueDTO;
import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import com.agromilk.br.request.OrdenhaRequestDTO;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface OrdenhaService {

    List<OrdenhaEntity> listar(
            Long idOrdenha,
            LocalDate data,
            Double primeiraOrdenha,
            Double segundaOrdenha,
            Long idAnimal,
            Long idTanque,
            Pageable pageable) throws Exception;
    void excluir(Long idOrdenha) throws Exception;
    OrdenhaEntity salvar(OrdenhaRequestDTO dto) throws Exception;
    OrdenhaEntity salvarOrdenha(OrdenhaRequestDTO dto) throws Exception;

    OrdenhaEntity atualizar(OrdenhaRequestDTO dto, Long idOrdenha) throws Exception;

    OrdenhaEntity findById(Long idOrdenha) throws ObjectNotFoundException, javassist.tools.rmi.ObjectNotFoundException;
    List<ProducaoLeiteMensalDTO> obterGraficoProducaoLeite();
    List<ProducaoLeiteDiariaDTO> obterSomaProducaoLeiteUltimos7Dias();
    List<ProducaoLeiteDiariaDTO> obterSomaProducaoLeiteUltimos7DiasAnimal(Long idAnimal);
    List<ProducaoLeiteSemanalDTO> obterGraficoProducaoLeitePorSemana();
    List<ProducaoLeiteMensalDTO> obterGraficoProducaoLeiteAnimal(Long idAnimal);
    List<ProducaoLeiteSemanalDTO> obterGraficoProducaoLeiteSemanalAnimal(Long idAnimal);

    List<TaxaOcupacaoTanqueDTO> obterGraficoTaxaOcupacaoTanques();







    }
