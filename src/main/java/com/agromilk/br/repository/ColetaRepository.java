package com.agromilk.br.repository;

import com.agromilk.br.entity.ColetaEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ColetaRepository extends JpaRepository<ColetaEntity, Long> {


    @Query( value = "SELECT coleta FROM ColetaEntity coleta "
            + " WHERE 1=1 "
            + " AND ( :idColeta IS NULL OR coleta.idColeta = :idColeta ) "
            + " AND ( :idTanque IS NULL OR coleta.tanque.idTanque = :idTanque ) "
            + " AND ( :idLaticinio IS NULL OR coleta.laticinio.idLaticinio = :idLaticinio ) "
            + " AND ( :quantidade IS NULL OR coleta.quantidade = :quantidade ) "
            + " AND ( :descricao IS NULL OR coleta.descricao = :descricao ) "
            + " AND ( :data IS NULL OR coleta.data = :data ) ")
    List<ColetaEntity> findByFilter(Long idColeta,
                                    Long idTanque,
                                    Long idLaticinio,
                                    Double quantidade,
                                    String descricao,
                                    LocalDate data,
                                    Pageable pageable);
}
