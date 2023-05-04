package com.agromilk.br.repository;


import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.MovimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovimentoRepository extends JpaRepository<MovimentoEntity, Long> {

    @Modifying
    @Query(value = "DELETE FROM MovimentoEntity movimento "
            + "WHERE movimento.animal.idAnimal = :idAnimal")
    void deleteByAnimalId(@Param("idAnimal") Long idAnimal);

    @Query("SELECT m FROM MovimentoEntity m WHERE m.animal.idAnimal = :idAnimal ORDER BY m.id")
    List<MovimentoEntity> findByAnimalId(@Param("idAnimal") Long idAnimal);


    @Modifying
    @Query(value = "DELETE FROM MovimentoEntity movimento "
            + "WHERE movimento.lote.idLote = :idLote")
    void deleteByLoteId(@Param("idLote") Long idLote);
    List<MovimentoEntity> findByAnimalOrderByDataEntradaDesc(AnimalEntity animal);
    MovimentoEntity findFirstByAnimalOrderByDataEntradaDesc(AnimalEntity animal);
    @Query("SELECT m FROM MovimentoEntity m WHERE m.animal = :animal ORDER BY m.id DESC")
    MovimentoEntity findFirstByAnimalOrderByidMovimentoDesc(@Param("animal") AnimalEntity animal);

    @Query("SELECT m FROM MovimentoEntity m WHERE m.animal = :animal AND m.dataSaida IS NULL ORDER BY m.id DESC")
    MovimentoEntity findLastUnfinishedMovementByAnimal(@Param("animal") AnimalEntity animal);

}

