package com.agromilk.br.service;

import com.agromilk.br.constants.TanqueConstants;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.TanqueRepository;
import com.agromilk.br.request.TanqueRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
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
public class TanqueServiceImpl implements TanqueService {

    @Autowired
    private TanqueRepository tanqueRepository;




    public TanqueServiceImpl(TanqueRepository tanqueRepository) {
        this.tanqueRepository = tanqueRepository;
    }

    @Override
    public Long tanques(){
        return tanqueRepository.verificarQdtTanques();
    }

    @Override
    public Double quantidadeTotalLeite(){
        return tanqueRepository.somarQuantidadesAtuais();
    }
    @Override
    public void excluir(Long idTanque) throws Exception {

        Optional<TanqueEntity> optionalTanque = tanqueRepository.findById(idTanque);
        if(!optionalTanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        Double tanque = tanqueRepository.verificarLeite(idTanque);
        if(tanque > 0) {
            throw new Exception(TanqueConstants.TANQUE_CONTEM_LEITE);
        }
            tanqueRepository.deleteById(idTanque);
    }

    @Override
    public List<TanqueEntity> listar(
            Long idTanque,
            String descricao,
            Double capacidade,
            String modelo,
            LocalDate dataFabricacao,
            Boolean ativo,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<TanqueEntity> lista = tanqueRepository.findByFilter(
                idTanque,
                descricao,
                capacidade,
                modelo,
                dataFabricacao,
                ativo,
                pageable);

        return lista;
    }
    private TanqueEntity saveTanque(TanqueRequestDTO dto)
            throws NotFoundException {
        TanqueEntity saveTanque;
        if(nonNull(dto.getIdTanque())) {
            Optional<TanqueEntity> optionalTanque = tanqueRepository.findById(dto.getIdTanque());
            if (!optionalTanque.isPresent()) {
                throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
            }
            saveTanque = optionalTanque.get();
        }
        else {
            saveTanque = new TanqueEntity();
        }
        saveTanque.setDescricao(dto.getDescricao());
        saveTanque.setCapacidade(dto.getCapacidade());
        saveTanque.setModelo(dto.getModelo());
        saveTanque.setDataFabricacao(dto.getDataFabricacao());
        saveTanque.setAtivo(dto.getAtivo());
        saveTanque.setQuantidadeAtual(dto.getQuantidadeAtual());
        saveTanque = tanqueRepository.save(saveTanque);
        return saveTanque;

    }
    public TanqueEntity findById(Long idTanque) throws ObjectNotFoundException {
        Optional<TanqueEntity> obj = tanqueRepository.findById(idTanque);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idTanque + ", Tipo: " + TanqueEntity.class.getName()));
    }
    public TanqueEntity salvar(TanqueRequestDTO dto)
            throws NotFoundException {

        if (nonNull(dto.getIdTanque())) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_INSERT);
        }
        return saveTanque(dto);
    }

    public TanqueEntity atualizar(TanqueRequestDTO dto, Long idTanque) throws Exception {
        if (isNull(idTanque)) {
            throw new BadRequestException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        dto.setIdTanque(idTanque);

        return saveTanque(dto);
    }

}
