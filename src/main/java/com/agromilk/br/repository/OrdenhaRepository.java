package com.agromilk.br.repository;

import com.agromilk.br.entity.OrdenhaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrdenhaRepository extends JpaRepository<OrdenhaEntity, Long> {


    @Query( value = "SELECT ordenha FROM OrdenhaEntity ordenha "
            + " WHERE 1=1 "
            + " AND ( :idOrdenha IS NULL OR ordenha.idOrdenha = :idOrdenha ) "
            + " AND ( :data IS NULL OR ordenha.data = :data ) "
            + " AND ( :quantidade IS NULL OR ordenha.quantidade = :quantidade ) "
            + " AND ( :idAnimal IS NULL OR ordenha.animal.idAnimal = :idAnimal ) "
            + " AND ( :idTanque IS NULL OR ordenha.tanque.idTanque = :idTanque ) ")
    List<OrdenhaEntity> findByFilter(Long idOrdenha,
                                     LocalDate data,
                                     Long quantidade,
                                     Long idAnimal,
                                     Long idTanque,
                                     Pageable pageable);

    @Query( value = "SELECT AVG(ordenha.quantidade) FROM OrdenhaEntity ordenha ")
    Double verificarMediaLitro();

    //@Query( value = "SELECT ordenha.idAnimal,sum(ordenha.quantidade) FROM OrdenhaEntity ordenha"
    //+"GROUP BY ordenha.idAnimal")
    //Long quantidadeLeiteAnimal();

    @Query( value = "SELECT sum(ordenha.quantidade) FROM OrdenhaEntity ordenha")
    Long totalDeLeiteProduzido();




}
