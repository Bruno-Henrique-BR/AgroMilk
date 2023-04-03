package com.agromilk.br.service;

import com.agromilk.br.constants.LoteConstants;
import com.agromilk.br.constants.RacaConstants;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.LoteRepository;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
@Transactional
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private AnimalRepository animalRepository;

    public LoteServiceImpl(LoteRepository loteRepository, AnimalRepository animalRepository) {
        this.loteRepository = loteRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public List<LoteEntity> listar(
            Long idLote,
            String nomeLote,
            String descricao,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<LoteEntity> lista = loteRepository.findByFilter(
                idLote,
                nomeLote,
                descricao,
                pageable);

        return lista;
    }

    public LoteEntity findById(Long idLote) throws ObjectNotFoundException {
        Optional<LoteEntity> obj = loteRepository.findById(idLote);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idLote + ", Tipo: " + LoteEntity.class.getName()));
    }
    public void adicionarAnimal(Long idLote, AnimalEntity animal) throws ObjectNotFoundException {
        LoteEntity lote = loteRepository.findById(idLote)
                .orElseThrow(() -> new ObjectNotFoundException("Lote não encontrado"));

        List<AnimalEntity> animais = lote.getList();
        animais.add(animal);

        lote.setList(animais);
        loteRepository.save(lote);
    }

    @Override
    public void excluir(Long idLote) throws Exception {
        Boolean hasAnimal = animalRepository.existsByLoteIdLote(idLote);
        if(Boolean.TRUE.equals(hasAnimal)){
            throw new Exception(LoteConstants.IDLOTE_NOT_DELETED);
        }
        loteRepository.deleteById(idLote);
    }

    private LoteEntity saveLote(LoteRequestDTO dto)
            throws NotFoundException{
        LoteEntity saveLote;
        if(nonNull(dto.getIdLote())) {
            Optional<LoteEntity> optionalLote = loteRepository.findById(dto.getIdLote());
            if (!optionalLote.isPresent()) {
                throw new NotFoundException(LoteConstants.IDLOTE_NOTFOUND);
            }
            saveLote = optionalLote.get();
        }
        else {
            saveLote = new LoteEntity();
        }
        saveLote.setNomeLote(dto.getNomeLote());
        saveLote.setDescricao(dto.getDescricao());
        saveLote = loteRepository.save(saveLote);
        return saveLote;

    }

    public LoteEntity salvar(LoteRequestDTO dto)
            throws NotFoundException {

        if (nonNull(dto.getIdLote())) {
            throw new NotFoundException(LoteConstants.IDLOTE_INSERT);
        }
        return saveLote(dto);
    }

    public LoteEntity atualizar(LoteRequestDTO dto, Long idLote) throws Exception {
        if (isNull(idLote)) {
            throw new BadRequestException(LoteConstants.IDLOTE_NOTFOUND);
        }
        dto.setIdLote(idLote);

        return saveLote(dto);
    }
}