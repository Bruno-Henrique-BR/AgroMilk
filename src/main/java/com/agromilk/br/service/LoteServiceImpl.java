package com.agromilk.br.service;

import com.agromilk.br.constants.LoteConstants;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.LoteRepository;
import com.agromilk.br.request.LoteRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
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
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository loteRepository;

    public LoteServiceImpl(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    @Override
    public Page<LoteEntity> listar(
            Long idLote,
            String nome,
            String descricao,
            String sexo,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        Page<LoteEntity> lista = loteRepository.findByFilter(
                idLote,
                nome,
                descricao,
                sexo,
                pageable);

        return lista;
    }

    public LoteEntity findById(Long idLote) throws ObjectNotFoundException {
        Optional<LoteEntity> obj = loteRepository.findById(idLote);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idLote + ", Tipo: " + LoteEntity.class.getName()));
    }


    @Override
    public void excluir(Long idLote) throws Exception {
        LoteEntity obj = findById(idLote);

        if (obj.getList().size() > 0) {
            throw new Exception("O Lote possui animais, não pode ser deletado!");
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
        saveLote.setNome(dto.getNome());
        saveLote.setDescricao(dto.getDescricao());
        saveLote.setSexo(dto.getSexo());
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
