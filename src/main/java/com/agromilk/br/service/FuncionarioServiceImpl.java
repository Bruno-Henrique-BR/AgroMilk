package com.agromilk.br.service;

import com.agromilk.br.constants.FuncionarioConstants;
import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.exception.ConflictException;
import com.agromilk.br.repository.FuncionarioRepository;
import com.agromilk.br.request.FuncionarioRequestDTO;
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
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public List<FuncionarioEntity> listar(
            Long idFuncionario,
            String nome,
            String cpf,
            LocalDate dataNascimento,
            String endereco,
            String telefone,


            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<FuncionarioEntity> lista = funcionarioRepository.findByFilter(
                idFuncionario,
                nome,
                cpf,
                dataNascimento,
                endereco,
                telefone,

                pageable);

        return lista;
    }

    public FuncionarioEntity findById(Long idFuncionario) throws ObjectNotFoundException {
        Optional<FuncionarioEntity> obj = funcionarioRepository.findById(idFuncionario);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idFuncionario + ", Tipo: " + FuncionarioEntity.class.getName()));
    }
    private void validate(FuncionarioRequestDTO funcionario) throws NotFoundException {
        Boolean exists = this.funcionarioRepository.existsByCpf(funcionario.getCpf());
        Optional<FuncionarioEntity> optionalFuncionario = funcionarioRepository.findById(funcionario.getIdFuncionario());

        if (!optionalFuncionario.isPresent()) {

            if (Boolean.TRUE.equals(exists)) {
                throw new ConflictException(FuncionarioConstants.CPJEXISTS_CONFLICT);
            }
        }
    }

    @Override
    public void excluir(Long idFuncionario) throws Exception {

        funcionarioRepository.deleteById(idFuncionario);
    }

    private FuncionarioEntity saveFuncionario(FuncionarioRequestDTO dto)
            throws NotFoundException {

        FuncionarioEntity saveFuncionario;

        if(nonNull(dto.getIdFuncionario())) {
            Optional<FuncionarioEntity> optionalFuncionario = funcionarioRepository.findById(dto.getIdFuncionario());
            if (!optionalFuncionario.isPresent()) {
                throw new NotFoundException(FuncionarioConstants.IDFUNCIONARIO_NOTFOUND);
            }
            saveFuncionario = optionalFuncionario.get();
        }
        else {
            saveFuncionario = new FuncionarioEntity();
        }
        saveFuncionario.setNomeFuncionario(dto.getNomeFuncionario());
        saveFuncionario.setCpf(dto.getCpf());
        saveFuncionario.setDataNascimento(dto.getDataNascimento());
        saveFuncionario.setEndereco(dto.getEndereco());
        saveFuncionario.setTelefone(dto.getTelefone());
        saveFuncionario = funcionarioRepository.save(saveFuncionario);

        return saveFuncionario;

    }

    public FuncionarioEntity salvar(FuncionarioRequestDTO dto)
            throws NotFoundException {
        this.validate(dto);

        if (nonNull(dto.getIdFuncionario())) {
            throw new NotFoundException(FuncionarioConstants.IIDFUNCIONARIO_INSERT);
        }
        return saveFuncionario(dto);
    }

    public FuncionarioEntity atualizar(FuncionarioRequestDTO dto, Long idFuncionario) throws Exception {
        if (isNull(idFuncionario)) {
            throw new BadRequestException(FuncionarioConstants.IDFUNCIONARIO_NOTFOUND);
        }
        this.validate(dto);
        dto.setIdFuncionario(idFuncionario);

        return saveFuncionario(dto);
    }
}
