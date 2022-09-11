package com.agromilk.br.service;

import com.agromilk.br.constants.LoteConstants;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.MarcaRepository;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.request.MarcaRequestDTO;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public MarcaEntity findById(Long idMarca) throws ObjectNotFoundException {
        Optional<MarcaEntity> obj = marcaRepository.findById(idMarca);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idMarca + ", Tipo: " + MarcaEntity.class.getName()));
    }
    @Override
    public void excluir(Long idMarca) throws Exception {
        MarcaEntity obj = findById(idMarca);

        if (obj.getList().size() > 0) {
            throw new Exception("A marca possui tanques, não pode ser deletado!");
        }
        marcaRepository.deleteById(idMarca);
    }
    private MarcaEntity saveMarca(MarcaRequestDTO dto)
            throws NotFoundException {
        MarcaEntity saveMarca;
        if(nonNull(dto.getIdMarca())) {
            Optional<MarcaEntity> optionalMarca = marcaRepository.findById(dto.getIdMarca());
            if (!optionalMarca.isPresent()) {
                throw new NotFoundException(LoteConstants.IDLOTE_NOTFOUND);
            }
            saveMarca = optionalMarca.get();
        }
        else {
            saveMarca = new MarcaEntity();
        }
        saveMarca.setDescricao(dto.getDescricao());
        saveMarca = marcaRepository.save(saveMarca);
        return saveMarca;

    }

    public MarcaEntity salvar(MarcaRequestDTO dto)
            throws NotFoundException {

        if (nonNull(dto.getIdMarca())) {
            throw new NotFoundException(LoteConstants.IDLOTE_INSERT);
        }
        return saveMarca(dto);
    }

    public MarcaEntity atualizar(MarcaRequestDTO dto, Long idMarca) throws Exception {
        if (isNull(idMarca)) {
            throw new BadRequestException(LoteConstants.IDLOTE_NOTFOUND);
        }
        dto.setIdMarca(idMarca);

        return saveMarca(dto);
    }
}
