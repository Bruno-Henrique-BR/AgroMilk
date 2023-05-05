package com.agromilk.br.service;

import com.agromilk.br.constants.*;
import com.agromilk.br.entity.*;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.*;
import com.agromilk.br.request.AnimalRequestDTO;
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
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    private LoteRepository loteRepository;

    private RacaRepository racaRepository;

    private OrdenhaRepository ordenhaRepository;

    private MovimentoRepository movimentoRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, LoteRepository loteRepository, RacaRepository racaRepository, OrdenhaRepository ordenhaRepository, MovimentoRepository movimentoRepository) {
        this.animalRepository = animalRepository;
        this.loteRepository = loteRepository;
        this.racaRepository = racaRepository;
        this.ordenhaRepository = ordenhaRepository;
        this.movimentoRepository = movimentoRepository;
    }

    @Override
    @Transactional
    public void excluir(Long idAnimal) throws Exception {
        Optional<AnimalEntity> animalOptional = animalRepository.findById(idAnimal);
        if (!animalOptional.isPresent()) {
            throw new Exception("Animal não encontrado");
        }
        AnimalEntity animal = animalOptional.get();

        // Exclui todas as ordenhas relacionadas ao animal
        ordenhaRepository.deleteByAnimalId(idAnimal);

        // Exclui todas os movimentos relacionados ao animal
        movimentoRepository.deleteByAnimalId(idAnimal);

        // Exclui o animal
        animalRepository.deleteById(idAnimal);
    }

    @Override
    public Long animais(){
        return animalRepository.verificarQdtAnimais();
    }

    @Override
    public Long animaisLactacao(){
        return animalRepository.verificarQtdAnimaisLactantes();
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
    public List<AnimalEntity> pesquisar(String apelido) {
        return animalRepository.findByApelidoContainingIgnoreCase(apelido);
    }
    public List<AnimalEntity> findAnimaisNaoContemNoLote(Long idLote) {
        return animalRepository.findAnimaisNaoContemNoLote(idLote);
    }

    public AnimalEntity findById(Long idAnimal) throws ObjectNotFoundException {
        Optional<AnimalEntity> obj = animalRepository.findById(idAnimal);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idAnimal + ", Tipo: " + AnimalEntity.class.getName()));
    }

    @Override
    public Double porcentagemLactantes() {
        Double qtdAnimal = Double.valueOf(animalRepository.verificarQdtAnimais());
        Double qtdAnimalLactantes = Double.valueOf(animalRepository.verificarQtdAnimaisLactantes());
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

    public List<AnimalEntity> findByIdLote(Long idLote) {

        return animalRepository.findByIdLote(idLote);
    }
    @Override
    public List<AnimalEntity> listar(
            Long idAnimal,
            String codigo,
            String apelido,
            LocalDate dataNascimento,
            LocalDate dataCompra,
            String nomeLote,
            String nomeRaca,
            Double media,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<AnimalEntity> lista = animalRepository.findByFilter(
                idAnimal,
                codigo,
                apelido,
                dataNascimento,
                dataCompra,
                nomeLote,
                nomeRaca,
                media,
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
        saveAnimal.setLote(lote.get());
        saveAnimal.setRaca(raca.get());
        saveAnimal = animalRepository.save(saveAnimal);
        return saveAnimal;

    }

    public AnimalEntity salvar(AnimalRequestDTO dto)
            throws NotFoundException {

        if (nonNull(dto.getIdAnimal())) {
            throw new NotFoundException(AnimalConstants.IDANIMAL_INSERT);
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
    public List<AnimalEntity> listarAnimaisLactantes() {
        return animalRepository.listarAnimaisLactantes();

    }


    public List<AnimalEntity> listarPorIdLote(Long idLote) {
        return animalRepository.findByLoteIdLote(idLote);
    }

    public List<AnimalEntity> listarPorIdRaca(Long idRaca) {
        return animalRepository.findByRacaIdRaca(idRaca);
    }


}
