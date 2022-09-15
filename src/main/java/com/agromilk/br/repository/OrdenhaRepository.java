package com.agromilk.br.repository;

import com.agromilk.br.entity.OrdenhaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface OrdenhaRepository extends JpaRepository<OrdenhaEntity, Long> {


    @Query( value = "SELECT ordenha FROM OrdenhaEntity ordenha "
            + " WHERE 1=1 "
            + " AND ( :idOrdenha IS NULL OR ordenha.idOrdenha = :idOrdenha ) "
            + " AND ( :data IS NULL OR ordenha.data = :data ) "
            + " AND ( :quantidade IS NULL OR ordenha.quantidade = :quantidade ) "
            + " AND ( :idAnimal IS NULL OR ordenha.animal.idAnimal = :idAnimal ) "
            + " AND ( :idTanque IS NULL OR ordenha.tanque.idTanque = :idTanque ) "
            + " AND ( :nomeFuncionario IS NULL OR ordenha.funcionario.nomeFuncionario = :nomeFuncionario ) ")

    Page<OrdenhaEntity> findByFilter(Long idOrdenha,
                                  LocalDate data,
                                  Long quantidade,
                                  Long idAnimal,
                                  Long idTanque,
                                  String nomeFuncionario,
                                  Pageable pageable);
}
