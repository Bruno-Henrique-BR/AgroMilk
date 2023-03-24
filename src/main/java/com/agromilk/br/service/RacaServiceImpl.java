package com.agromilk.br.service;

import com.agromilk.br.constants.RacaConstants;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.entity.TanqueEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.RacaRepository;
import com.agromilk.br.request.RacaRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class RacaServiceImpl implements RacaService {

    @Autowired
    private RacaRepository racaRepository;

    private AnimalRepository animalRepository;

    public RacaServiceImpl(RacaRepository racaRepository, AnimalRepository animalRepository) {
        this.racaRepository = racaRepository;
        this.animalRepository = animalRepository;
    }
    public RacaEntity findById(Long idRaca) throws ObjectNotFoundException {
        Optional<RacaEntity> obj = racaRepository.findById(idRaca);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idRaca + ", Tipo: " + RacaEntity.class.getName()));
    }
    @Override
    public List<RacaEntity> listar(
            Long idRaca,
            String nomeRaca,
            String descricao,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<RacaEntity> lista = racaRepository.findByFilter(
                idRaca,
                nomeRaca,
                descricao,
                pageable);

        return lista;
    }

    @Override
    public void excluir(Long idRaca) throws Exception {

        Boolean hasAnimal = animalRepository.existsByRacaIdRaca(idRaca);
        if(Boolean.TRUE.equals(hasAnimal)){
            throw new Exception(RacaConstants.IDRACA_NOT_DELETED);
        }
        racaRepository.deleteById(idRaca);
    }

    private RacaEntity saveRaca(RacaRequestDTO dto)
            throws NotFoundException {
        RacaEntity saveRaca;
        if(nonNull(dto.getIdRaca())) {
            Optional<RacaEntity> optionalRaca = racaRepository.findById(dto.getIdRaca());
            if (!optionalRaca.isPresent()) {
                throw new NotFoundException(RacaConstants.IDRACA_NOTFOUND);
            }
            saveRaca = optionalRaca.get();
        }
        else {
            saveRaca = new RacaEntity();
        }
        saveRaca.setNomeRaca(dto.getNomeRaca());
        saveRaca.setDescricao(dto.getDescricao());
        saveRaca = racaRepository.save(saveRaca);
        return saveRaca;

    }

    public RacaEntity salvar(RacaRequestDTO dto)
            throws NotFoundException {

        if (nonNull(dto.getIdRaca())) {
            throw new NotFoundException(RacaConstants.IDRACA_INSERT);
        }
        return saveRaca(dto);
    }

    public RacaEntity atualizar(RacaRequestDTO dto, Long idRaca) throws Exception {
        if (isNull(idRaca)) {
            throw new BadRequestException(RacaConstants.IDRACA_NOTFOUND);
        }
        dto.setIdRaca(idRaca);

        return saveRaca(dto);
    }

}