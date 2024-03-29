package com.agromilk.br.service;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.request.AnimalRequestDTO;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AnimalService {



    List<AnimalEntity> listar(
            Long idAnimal,
            String codigo,
            String apelido,
            LocalDate dataNascimento,
            LocalDate dataCompra,
            String nomeLote,
            String nomeRaca,
            Double media,
            Pageable pageable) throws Exception;
    List<AnimalEntity> findByIdLote(Long idLote);

    void excluir(Long idAnimal) throws Exception;

    AnimalEntity salvar(AnimalRequestDTO dto) throws NotFoundException;

    AnimalEntity atualizar(AnimalRequestDTO dto, Long idAnimal) throws Exception;
    List<AnimalEntity> listarPorIdLote(Long idLote);

    List<AnimalEntity> listarPorIdRaca(Long idRaca);


    AnimalEntity findById(Long idAnimal) throws ObjectNotFoundException, javassist.tools.rmi.ObjectNotFoundException;
    List<AnimalEntity> listarAnimaisLactantes();

    Long animais();

    Long animaisLactacao();

    Long animaisSeca();

    Long animaisGestacao();
    Double animaisMediaLitro();

    Double porcentagemLactantes();

    Double porcentagemSecas();

    Double porcentagemGestantes();


    List<AnimalEntity> pesquisar(String apelido) throws Exception;

    List<AnimalEntity> findAnimaisNaoContemNoLote(Long idLote);

    List<AnimalEntity> getMelhoresVacas();

    List<AnimalEntity> getPioresVacas();


}
