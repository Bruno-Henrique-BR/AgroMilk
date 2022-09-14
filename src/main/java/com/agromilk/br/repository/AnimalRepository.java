package com.agromilk.br.repository;

import com.agromilk.br.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

   Boolean existsByRacaIdRaca(Long idRaca);

   Boolean existsByLoteIdLote(Long idLote);

   @Query( value = "SELECT animal FROM AnimalEntity animal "
           + " WHERE 1=1 "
           + " AND ( :idAnimal IS NULL OR animal.idAnimal = :idAnimal ) "
           + " AND ( :codigo IS NULL OR animal.codigo LIKE :codigo ) "
           + " AND ( :apelido IS NULL OR animal.apelido LIKE :apelido ) "
           + " AND ( :dataNascimento IS NULL OR animal.dataNascimento = :dataNascimento ) "
           + " AND ( :dataCompra IS NULL OR animal.dataCompra = :dataCompra ) "
           + " AND ( :cor IS NULL OR animal.cor LIKE :cor ) "
           + " AND ( :nomeLote IS NULL OR animal.lote.nomeLote = :nomeLote ) "
           + " AND ( :nomeRaca IS NULL OR animal.raca.nomeRaca = :nomeRaca ) "
           + " AND ( :lactacao IS NULL OR animal.lactacao = :lactacao ) ")
   Page<AnimalEntity> findByFilter(Long idAnimal,
                                   String codigo,
                                   String apelido,
                                   LocalDate dataNascimento,
                                   LocalDate dataCompra,
                                   String cor,
                                   String nomeLote,
                                   String nomeRaca,
                                   Boolean lactacao,
                                   Pageable pageable);

}


