package com.agromilk.br.service;

import com.agromilk.br.constants.LaticinioConstants;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.LaticinioEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.exception.ConflictException;
import com.agromilk.br.repository.LaticinioRepository;
import com.agromilk.br.request.LaticinioRequestDTO;
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
public class LaticinioServiceImpl implements LaticinioService {

    @Autowired
    private LaticinioRepository laticinioRepository;

    public  LaticinioServiceImpl(LaticinioRepository laticinioRepository) {
        this.laticinioRepository = laticinioRepository;
    }

    @Override
    public List<LaticinioEntity> listar(
            Long idLaticinio,
            String razaoSocial,
            String cnpj,
            String endereco,
            String telefone,

            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<LaticinioEntity> lista = laticinioRepository.findByFilter(
                idLaticinio,
                razaoSocial,
                cnpj,
                endereco,
                telefone,
                pageable);

        return lista;
    }
    public LaticinioEntity findById(Long idLaticinio) throws ObjectNotFoundException {
        Optional<LaticinioEntity> obj = laticinioRepository.findById(idLaticinio);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idLaticinio + ", Tipo: " + LaticinioEntity.class.getName()));
    }
    private void validate(LaticinioRequestDTO laticinio) throws NotFoundException {
        Boolean exists = this.laticinioRepository.existsByCnpj(laticinio.getCnpj());
        if (laticinio.getCnpj() != null && Boolean.TRUE.equals(exists)){
            throw new ConflictException(LaticinioConstants.CNPJEXISTS_CONFLICT);
        }
    }

    @Override
    public void excluir(Long idLaticinio) throws Exception {

        laticinioRepository.deleteById(idLaticinio);
    }

    private LaticinioEntity saveLaticinio(LaticinioRequestDTO dto)
            throws NotFoundException {

        LaticinioEntity saveLaticinio;

        if(nonNull(dto.getIdLaticinio())) {
            Optional<LaticinioEntity> optionalLaticinio = laticinioRepository.findById(dto.getIdLaticinio());

            if (!optionalLaticinio.isPresent()) {
                throw new NotFoundException(LaticinioConstants.IDLATICINIO_NOTFOUND);
            }
            saveLaticinio = optionalLaticinio.get();
        }
        else {
            this.validate(dto);
            saveLaticinio = new LaticinioEntity();
        }
        saveLaticinio.setRazaoSocial(dto.getRazaoSocial());
        saveLaticinio.setCnpj(dto.getCnpj());
        saveLaticinio.setEndereco(dto.getEndereco());
        saveLaticinio.setTelefone(dto.getTelefone());
        saveLaticinio = laticinioRepository.save(saveLaticinio);

        return saveLaticinio;

    }

    public LaticinioEntity salvar(LaticinioRequestDTO dto)
            throws NotFoundException {

        if (nonNull(dto.getIdLaticinio())) {
            throw new NotFoundException(LaticinioConstants.IDLATICINIO_INSERT);
        }
        dto.setCnpj(dto.getCnpj());

        return saveLaticinio(dto);
    }

    public LaticinioEntity atualizar(LaticinioRequestDTO dto, Long idLaticinio) throws Exception {
        if (isNull(idLaticinio)) {
            throw new BadRequestException(LaticinioConstants.IDLATICINIO_NOTFOUND);
        }

        dto.setIdLaticinio(idLaticinio);
        return saveLaticinio(dto);
    }


}
