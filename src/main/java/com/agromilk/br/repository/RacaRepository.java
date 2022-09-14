package com.agromilk.br.repository;

import com.agromilk.br.entity.RacaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RacaRepository extends JpaRepository<RacaEntity, Long> {

    @Query( value = "SELECT raca FROM RacaEntity raca "
            + " WHERE 1=1 "
            + " AND ( :idRaca IS NULL OR raca.idRaca = :idRaca ) "
            + " AND ( :nomeRaca IS NULL OR raca.nomeRaca LIKE :nomeRaca ) "
            + " AND ( :descricao IS NULL OR raca.descricao LIKE :descricao ) ")
    Page<RacaEntity> findByFilter(Long idRaca,
                                   String nomeRaca,
                                   String descricao,
                                   Pageable pageable);

}
