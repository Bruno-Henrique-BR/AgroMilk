package com.agromilk.br.service;

import com.agromilk.br.constants.LoteConstants;
import com.agromilk.br.constants.RacaConstants;
import com.agromilk.br.entity.RacaEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.RacaRepository;
import com.agromilk.br.request.RacaRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
public class RacaServiceImpl implements RacaService {

    @Autowired
    private RacaRepository racaRepository;

    public RacaServiceImpl(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    @Override
    public Page<RacaEntity> listar(
            Long idRaca,
            String nome,
            String descricao,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        Page<RacaEntity> lista = racaRepository.findByFilter(
                idRaca,
                nome,
                descricao,
                pageable);

        return lista;
    }


    @Override
    public void excluir(Long idRaca) throws Exception {

        racaRepository.deleteById(idRaca);
    }

    private RacaEntity saveRaca(RacaRequestDTO dto)
            throws NotFoundException {
        RacaEntity saveRaca;
        if(nonNull(dto.getIdRaca())) {
            Optional<RacaEntity> optionalRaca = racaRepository.findById(dto.getIdRaca());
            if (!optionalRaca.isPresent()) {
                throw new NotFoundException(LoteConstants.IDLOTE_NOTFOUND);
            }
            saveRaca = optionalRaca.get();
        }
        else {
            saveRaca = new RacaEntity();
        }
        saveRaca.setNome(dto.getNome());
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
