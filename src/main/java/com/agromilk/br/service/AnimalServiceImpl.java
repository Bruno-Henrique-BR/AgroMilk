package com.agromilk.br.service;

import com.agromilk.br.constants.*;
import com.agromilk.br.entity.*;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.LoteRepository;
import com.agromilk.br.repository.OrdenhaRepository;
import com.agromilk.br.repository.RacaRepository;
import com.agromilk.br.request.AnimalRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
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
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    private LoteRepository loteRepository;

    private RacaRepository racaRepository;

    private OrdenhaRepository ordenhaRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, LoteRepository loteRepository, RacaRepository racaRepository, OrdenhaRepository ordenhaRepository) {
        this.animalRepository = animalRepository;
        this.loteRepository = loteRepository;
        this.racaRepository = racaRepository;
        this.ordenhaRepository = ordenhaRepository;
    }

    @Override
    public void excluir(Long idAnimal) throws Exception {
        animalRepository.deleteById(idAnimal);
    }

    @Override
    public Long animais(){
        return animalRepository.verificarQdtAnimais();
    }

    @Override
    public Long animaisLactacao(){
        return animalRepository.verificarQdtAnimaisLactacao();
    }

    @Override
    public Long animaisSeca(){
        return animalRepository.verificarQdtAnimaisSecas();
    }

    @Override
    public Double animaisMediaLitro(){
        return ordenhaRepository.verificarMediaLitro();
    }

    @Override
    public Page<AnimalEntity> listarAnimais(
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());
         Double quantidade = ordenhaRepository.verificarMediaLitro();
        Page<AnimalEntity> lista = animalRepository.animaisOrdenhaAbaixoMedia(
                quantidade,
                pageable);

        return lista;
    }

    @Override
    public Double porcentagemLactantes() {
        Double qtdAnimal = Double.valueOf(animalRepository.verificarQdtAnimais());
        Double qtdAnimalLactantes = Double.valueOf(animalRepository.verificarQdtAnimaisLactacao());
        Double media = 100/qtdAnimal*qtdAnimalLactantes;
        return media;
    }

    @Override
    public Double porcentagemSecas() {
        Double qtdAnimal = Double.valueOf(animalRepository.verificarQdtAnimais());
        Double qtdAnimalNaoLactantes = Double.valueOf(animalRepository.verificarQdtAnimaisSecas());
        Double media = 100/qtdAnimal*qtdAnimalNaoLactantes;
        return media;
    }


    @Override
    public List<AnimalEntity> listar(
            Long idAnimal,
            String codigo,
            String apelido,
            LocalDate dataNascimento,
            LocalDate dataCompra,
            String cor,
            String nomeLote,
            String nomeRaca,
            Boolean lactacao,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<AnimalEntity> lista = animalRepository.findByFilter(
                idAnimal,
                codigo,
                apelido,
                dataNascimento,
                dataCompra,
                cor,
                nomeLote,
                nomeRaca,
                lactacao,
                pageable);

        return lista;
    }

    private AnimalEntity saveAnimal(AnimalRequestDTO dto)
            throws NotFoundException {
        Optional<LoteEntity> lote = loteRepository
                .findById(dto.getIdLote());
        if (!lote.isPresent()) {
            throw new NotFoundException(LoteConstants.IDLOTE_NOTFOUND);
        }

        Optional<RacaEntity> raca = racaRepository
                .findById(dto.getIdRaca());
        if (!raca.isPresent()) {
            throw new NotFoundException(RacaConstants.IDRACA_NOTFOUND);
        }
        AnimalEntity saveAnimal;
        if(nonNull(dto.getIdAnimal())) {
            Optional<AnimalEntity> optionalAnimal = animalRepository.findById(dto.getIdAnimal());
            if (!optionalAnimal.isPresent()) {
                throw new NotFoundException(AnimalConstants.IDANIMAL_NOTFOUND);
            }
            saveAnimal = optionalAnimal.get();
        }
        else {
            saveAnimal = new AnimalEntity();
        }
        saveAnimal.setCodigo(dto.getCodigo());
        saveAnimal.setApelido(dto.getApelido());
        saveAnimal.setDataNascimento(dto.getDataNascimento());
        saveAnimal.setDataCompra(dto.getDataCompra());
        saveAnimal.setCor(dto.getCor());
        saveAnimal.setLote(lote.get());
        saveAnimal.setRaca(raca.get());
        saveAnimal.setLactacao(dto.getLactacao());

        saveAnimal = animalRepository.save(saveAnimal);
        return saveAnimal;

    }

    public AnimalEntity salvar(AnimalRequestDTO dto)
            throws NotFoundException {

        if (nonNull(dto.getIdAnimal())) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_INSERT);
        }
        return saveAnimal(dto);
    }

    public AnimalEntity atualizar(AnimalRequestDTO dto, Long idAnimal) throws Exception {
        if (isNull(idAnimal)) {
            throw new BadRequestException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        dto.setIdAnimal(idAnimal);

        return saveAnimal(dto);
    }

}
