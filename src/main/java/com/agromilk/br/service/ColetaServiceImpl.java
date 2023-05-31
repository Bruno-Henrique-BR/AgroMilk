package com.agromilk.br.service;


import com.agromilk.br.constants.*;
import com.agromilk.br.entity.*;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.*;
import com.agromilk.br.request.ColetaRequestDTO;
import com.agromilk.br.request.OrdenhaRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
@Transactional
public class ColetaServiceImpl implements ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    private TanqueRepository tanqueRepository;

    private LaticinioRepository laticinioRepository;

    public ColetaServiceImpl(ColetaRepository coletaRepository, TanqueRepository tanqueRepository, LaticinioRepository laticinioRepository) {
        this.coletaRepository = coletaRepository;
        this.tanqueRepository = tanqueRepository;
        this.laticinioRepository = laticinioRepository;
    }


    @Override
    public void excluir(Long idColeta) throws Exception {
        coletaRepository.deleteById(idColeta);
    }

    @Override
    public List<ColetaEntity> listar(
            Long idColeta,
            Long idTanque,
            Long idLaticinio,
            Double quantidade,
            String descricao,
            LocalDate data,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<ColetaEntity> lista = coletaRepository.findByFilter(
                idColeta,
                idTanque,
                idLaticinio,
                quantidade,
                descricao,
                data,
                pageable);

        return lista;
    }

    private ColetaEntity saveColeta(ColetaRequestDTO dto)
            throws Exception {

        Optional<TanqueEntity> tanque = tanqueRepository
                .findById(dto.getIdTanque());
        if (!tanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        Optional<LaticinioEntity> laticinio = laticinioRepository
                .findById(dto.getIdLaticinio());
        if (!laticinio.isPresent()) {
            throw new NotFoundException(LaticinioConstants.IDLATICINIO_NOTFOUND);
        }
        ColetaEntity saveColeta;
        if(nonNull(dto.getIdColeta())) {
            Optional<ColetaEntity> optionalColeta = coletaRepository.findById(dto.getIdColeta());
            if (!optionalColeta.isPresent()) {
                throw new NotFoundException(OrdenhaConstants.IDORDENHA_NOTFOUND);
            }
            saveColeta = optionalColeta.get();
        }
        else {
            saveColeta = new ColetaEntity();
        }
        saveColeta.setTanque(tanque.get());
        saveColeta.setLaticinio(laticinio.get());
        saveColeta.setQuantidade(dto.getQuantidade());
        saveColeta.setDescricao(dto.getDescricao());
        saveColeta.setData(dto.getData());

        this.validate(dto);
        saveColeta = coletaRepository.save(saveColeta);
        return saveColeta;

    }

    private void validate(ColetaRequestDTO dto) throws Exception {
        Optional<TanqueEntity> tanque = tanqueRepository
                .findById(dto.getIdTanque());
        if (!tanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        if(dto.getQuantidade() < 0){
            throw new Exception(TanqueConstants.TANQUE_VALOR_INVALID);
        }
        Double quantidadeAtualTanque = tanque.get().getQuantidadeAtual();
        Double coleta = dto.getQuantidade();
        if(coleta <= quantidadeAtualTanque) {
            tanqueRepository.enviarLeiteColeta(tanque.get().getIdTanque(), dto.getQuantidade());
        }else{
            throw new Exception(TanqueConstants.TANQUE_EMPTY);
        }
    }

    public ColetaEntity salvar(ColetaRequestDTO dto)
            throws Exception {

        if (nonNull(dto.getIdColeta())) {
            throw new NotFoundException(ColetaConstants.IDCOLETA_INSERT);
        }
        return saveColeta(dto);
    }

    public ColetaEntity atualizar(ColetaRequestDTO dto, Long idColeta) throws Exception {
        if (isNull(idColeta)) {
            throw new BadRequestException(ColetaConstants.IDCOLETA_NOTFOUND);
        }
        dto.setIdColeta(idColeta);

        return saveColeta(dto);
    }
    public ColetaEntity findById(Long idColeta) throws javassist.tools.rmi.ObjectNotFoundException {
        Optional<ColetaEntity> obj = coletaRepository.findById(idColeta);
        return obj.orElseThrow(() -> new javassist.tools.rmi.ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idColeta + ", Tipo: " + ColetaEntity.class.getName()));
    }
    public Integer obterQuantidadeColetasUltimos30Dias() {
        LocalDate dataInicial = LocalDate.now().minusDays(30);
        LocalDate dataFinal = LocalDate.now();
        return coletaRepository.obterQuantidadeColetasUltimos30Dias(dataInicial, dataFinal);
    }
}
