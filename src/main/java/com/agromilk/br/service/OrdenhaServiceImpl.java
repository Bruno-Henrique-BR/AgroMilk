package com.agromilk.br.service;


import com.agromilk.br.constants.*;
import com.agromilk.br.entity.*;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.repository.AnimalRepository;
import com.agromilk.br.repository.FuncionarioRepository;
import com.agromilk.br.repository.OrdenhaRepository;
import com.agromilk.br.repository.TanqueRepository;
import com.agromilk.br.request.OrdenhaRequestDTO;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
@Transactional
public class OrdenhaServiceImpl implements OrdenhaService {

    @Autowired
    private OrdenhaRepository ordenhaRepository;

    private AnimalRepository animalRepository;

    private FuncionarioRepository funcionarioRepository;

    private TanqueRepository tanqueRepository;

    public OrdenhaServiceImpl(OrdenhaRepository ordenhaRepository, AnimalRepository animalRepository, FuncionarioRepository funcionarioRepository, TanqueRepository tanqueRepository) {
        this.ordenhaRepository = ordenhaRepository;
        this.animalRepository = animalRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.tanqueRepository = tanqueRepository;
    }

    public void cadastrarOrdenhas(List<OrdenhaRequestDTO> listaOrdenhas) throws Exception {
        for (OrdenhaRequestDTO dto : listaOrdenhas) {
            // Validação dos dados
            if (isNull(dto.getIdAnimal())) {
                throw new BadRequestException(AnimalConstants.IDANIMAL_NOTFOUND);
            }
            if (isNull(dto.getIdTanque())) {
                throw new BadRequestException(TanqueConstants.IDTANQUE_NOTFOUND);
            }
            if (isNull(dto.getData())) {
                throw new BadRequestException(OrdenhaConstants.ORDENHA_DATA_NOTFOUND);
            }
            if (dto.getQuantidade() == null || dto.getQuantidade() <= 0) {
                throw new BadRequestException(OrdenhaConstants.ORDENHA_QUANTIDADE_INVALID);
            }
            Optional<TanqueEntity> tanque = tanqueRepository.findById(dto.getIdTanque());
            if (!tanque.isPresent()) {
                throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
            }
            Double soma = dto.getQuantidade() + tanque.get().getQuantidadeAtual();
            Double capacidadeTanque = tanque.get().getCapacidade();
            if (soma > capacidadeTanque) {
                throw new Exception(TanqueConstants.TANQUE_FULL);
            }

            // Persistência da ordenha
            OrdenhaEntity ordenha = new OrdenhaEntity();
            ordenha.setData(dto.getData());
            ordenha.setQuantidade(dto.getQuantidade());
            Optional<AnimalEntity> animal = animalRepository.findById(dto.getIdAnimal());
            if (!animal.isPresent()) {
                throw new NotFoundException(AnimalConstants.IDANIMAL_NOTFOUND);
            }
            ordenha.setAnimal(animal.get());
            ordenha.setTanque(tanque.get());
            ordenhaRepository.save(ordenha);

            // Atualização da quantidade do tanque
            tanqueRepository.enviarLeiteTanque(tanque.get().getIdTanque(), dto.getQuantidade());
        }
    }

    @Override
    public void excluir(Long idOrdenha) throws Exception {
        ordenhaRepository.deleteById(idOrdenha);
    }

    @Override
    public List<OrdenhaEntity> listar(
            Long idOrdenha,
            LocalDate data,
            Long quantidade,
            Long idAnimal,
            Long idTanque,
            Pageable pageable) throws Exception {

        pageable = PageRequest.of(Paginacao.getPageOffsetFromPageable(pageable), pageable.getPageSize(), pageable.getSort());

        List<OrdenhaEntity> lista = ordenhaRepository.findByFilter(
                idOrdenha,
                data,
                quantidade,
                idAnimal,
                idTanque,
                pageable);

        return lista;
    }

    private OrdenhaEntity saveOrdenha(OrdenhaRequestDTO dto)
            throws Exception {
        Optional<AnimalEntity> animal = animalRepository
                .findById(dto.getIdAnimal());
        if (!animal.isPresent()) {
            throw new NotFoundException(AnimalConstants.IDANIMAL_NOTFOUND);
        }

//        Optional<FuncionarioEntity> funcionario = funcionarioRepository
//                .findById(dto.getIdFuncionario());
//        if (!funcionario.isPresent()) {
//            throw new NotFoundException(FuncionarioConstants.IDFUNCIONARIO_NOTFOUND);
//        }

        Optional<TanqueEntity> tanque = tanqueRepository
                .findById(dto.getIdTanque());
        if (!tanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        OrdenhaEntity saveOrdenha;
        if(nonNull(dto.getIdOrdenha())) {
            Optional<OrdenhaEntity> optionalOrdenha = ordenhaRepository.findById(dto.getIdOrdenha());
            if (!optionalOrdenha.isPresent()) {
                throw new NotFoundException(OrdenhaConstants.IDORDENHA_NOTFOUND);
            }
            saveOrdenha = optionalOrdenha.get();
        }
        else {
            saveOrdenha = new OrdenhaEntity();
        }
        saveOrdenha.setData(dto.getData());
        saveOrdenha.setQuantidade(dto.getQuantidade());
        saveOrdenha.setAnimal(animal.get());
        saveOrdenha.setTanque(tanque.get());
//        saveOrdenha.setFuncionario(funcionario.get());
        this.validate(dto);
        saveOrdenha = ordenhaRepository.save(saveOrdenha);
        return saveOrdenha;

    }

    private void validate(OrdenhaRequestDTO dto) throws Exception {
        Optional<TanqueEntity> tanque = tanqueRepository
                .findById(dto.getIdTanque());
        if (!tanque.isPresent()) {
            throw new NotFoundException(TanqueConstants.IDTANQUE_NOTFOUND);
        }
        if(dto.getQuantidade() < 0){
            throw new Exception(TanqueConstants.TANQUE_VALOR_INVALID);
        }
        Double soma = dto.getQuantidade() + tanque.get().getQuantidadeAtual();
        Double capacidadeTanque = tanque.get().getCapacidade();
        if(soma <= capacidadeTanque) {
            tanqueRepository.enviarLeiteTanque(tanque.get().getIdTanque(), dto.getQuantidade());
        }else{
            throw new Exception(TanqueConstants.TANQUE_FULL);
        }
    }

    public OrdenhaEntity salvar(OrdenhaRequestDTO dto)
            throws Exception {

        if (nonNull(dto.getIdOrdenha())) {
            throw new NotFoundException(OrdenhaConstants.IDORDENHA_INSERT);
        }
        return saveOrdenha(dto);
    }

    public OrdenhaEntity atualizar(OrdenhaRequestDTO dto, Long idOrdenha) throws Exception {
        if (isNull(idOrdenha)) {
            throw new BadRequestException(OrdenhaConstants.IDORDENHA_NOTFOUND);
        }
        dto.setIdOrdenha(idOrdenha);

        return saveOrdenha(dto);
    }

    public OrdenhaEntity findById(Long idOrdenha) throws javassist.tools.rmi.ObjectNotFoundException {
        Optional<OrdenhaEntity> obj = ordenhaRepository.findById(idOrdenha);
        return obj.orElseThrow(() -> new javassist.tools.rmi.ObjectNotFoundException(
                "Objeto não encontrado! Id: " + idOrdenha + ", Tipo: " + OrdenhaEntity.class.getName()));
    }



}
