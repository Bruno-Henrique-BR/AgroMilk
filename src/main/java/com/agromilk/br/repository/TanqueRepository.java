package com.agromilk.br.repository;

import com.agromilk.br.dto.TanqueDTO;
import com.agromilk.br.entity.MarcaEntity;
import com.agromilk.br.entity.TanqueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface TanqueRepository extends JpaRepository<TanqueEntity, Long> {
    @Query( value = "SELECT tanque FROM TanqueEntity tanque "
            + " WHERE 1=1 "
            + " AND ( :idTanque IS NULL OR tanque.idTanque = :idTanque ) "
            + " AND ( :descricao IS NULL OR tanque.descricao LIKE :descricao ) "
            + " AND ( :capacidade IS NULL OR tanque.capacidade = :capacidade ) "
            + " AND ( :nomeMarca IS NULL OR tanque.marca.nomeMarca = :nomeMarca ) "
            + " AND ( :modelo IS NULL OR tanque.modelo LIKE :modelo ) "
            + " AND ( :dataFabricacao IS NULL OR tanque.dataFabricacao = :dataFabricacao ) "
            + " AND ( :ativo IS NULL OR tanque.ativo = :ativo ) ")
    Page<TanqueDTO> findByFilter(Long idTanque,
                                 String descricao,
                                 Double capacidade,
                                 String nomeMarca,
                                 String modelo,
                                 LocalDate dataFabricacao,
                                 Boolean ativo,
                                 Pageable pageable);
}
