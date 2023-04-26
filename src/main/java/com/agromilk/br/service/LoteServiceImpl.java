package com.agromilk.br.service;

import com.agromilk.br.constants.LoteConstants;
import com.agromilk.br.constants.RacaConstants;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.MovimentoEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.LoteRepository;
import com.agromilk.br.repository.MovimentoRepository;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private MovimentoRepository movimentoRepository;

    public LoteServiceImpl(LoteRepository loteRepository, AnimalRepository animalRepository, MovimentoRepository movimentoRepository) {
        this.loteRepository = loteRepository;
        this.animalRepository = animalRepository;
        this.movimentoRepository = movimentoRepository;
    }
    @Override
    public Long lotes(){
        return loteRepository.verificarQdtLotes();
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

    public void adicionarAnimalEMovimento(Long idLote, Long idAnimal) throws Exception {
        LoteEntity lote = loteRepository.findById(idLote).orElseThrow(() -> new ObjectNotFoundException("Lote não encontrado."));
        AnimalEntity animal = animalRepository.findById(idAnimal).orElseThrow(() -> new ObjectNotFoundException("Animal não encontrado."));

        // Verificar se o animal já está no lote atual
        if (animal.getLote() != null && animal.getLote().getIdLote().equals(lote.getIdLote())) {
            throw new Exception("O animal já está no lote atual.");
        }


        // Salvar data de saída do animal do lote anterior
        LoteEntity loteAnterior = animal.getLote();
        if (loteAnterior != null && !loteAnterior.equals(lote)) {
            MovimentoEntity ultimoMovimento = movimentoRepository.findFirstByAnimalOrderByDataEntradaDesc(animal);
            if (ultimoMovimento != null) {
                ultimoMovimento.setDataSaida(LocalDate.now());
                movimentoRepository.save(ultimoMovimento);
            }
        }

        // Adicionar animal ao lote atual
        lote.getAnimais().add(animal);
        animal.setLote(lote);
        loteRepository.save(lote);
        // Atualizar lote do animal
        animal.setLote(lote);
        animalRepository.save(animal);

        // Salvar movimento
        MovimentoEntity movimento = new MovimentoEntity();
        movimento.setDataEntrada(LocalDate.now());
        movimento.setAnimal(animal);
        movimento.setLote(lote);
        movimentoRepository.save(movimento);
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