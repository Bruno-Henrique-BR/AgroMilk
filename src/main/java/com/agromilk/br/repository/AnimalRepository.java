package com.agromilk.br.repository;

import com.agromilk.br.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

   Boolean existsByRacaIdRaca(Long idRaca);
   @Query("SELECT a FROM AnimalEntity a WHERE a.lote IS NULL OR a.lote != :lote")
   List<AnimalEntity> findAnimaisSemLoteOuLoteDiferente(@Param("lote") LoteEntity lote);
   Boolean existsByLoteIdLote(Long idLote);
   List<AnimalEntity> findByApelidoContainingIgnoreCase(String apelido);

   @Query(value = "SELECT animal FROM AnimalEntity animal "
           + " WHERE 1=1 "
           + " AND ( :idAnimal IS NULL OR animal.idAnimal = :idAnimal ) "
           + " AND ( :codigo IS NULL OR animal.codigo LIKE :codigo ) "
           + " AND ( :apelido IS NULL OR animal.apelido LIKE :apelido ) "
           + " AND ( :dataNascimento IS NULL OR animal.dataNascimento = :dataNascimento ) "
           + " AND ( :dataCompra IS NULL OR animal.dataCompra = :dataCompra ) "
           + " AND ( :nomeLote IS NULL OR animal.lote.nomeLote = :nomeLote ) "
           + " AND ( :nomeRaca IS NULL OR animal.raca.nomeRaca = :nomeRaca ) "
           + " AND ( :media IS NULL OR animal.media = :media ) "
           + " ORDER BY animal.idAnimal")
   List<AnimalEntity> findByFilter(Long idAnimal,
                                   String codigo,
                                   String apelido,
                                   LocalDate dataNascimento,
                                   LocalDate dataCompra,
                                   String nomeLote,
                                   String nomeRaca,
                                   Double media,
                                   Pageable pageable);

   @Query( value = "SELECT COUNT(animal.idAnimal) FROM AnimalEntity  animal " )
   Long verificarQdtAnimais();

   @Query(value = "SELECT COUNT(animal.idAnimal) FROM AnimalEntity animal "
           + "WHERE animal.lote.tipoLote = 'LACTANTES'")
   Long verificarQtdAnimaisLactantes();


   @Query( value = "SELECT COUNT(animal.idAnimal) FROM AnimalEntity  animal "
           + " WHERE animal.lote.tipoLote = 'SECAS' ")
   Long verificarQdtAnimaisSecas();


   @Query("SELECT animal FROM AnimalEntity animal JOIN animal.lote lote WHERE lote.tipoLote = 'LACTANTES'")
   List<AnimalEntity> listarAnimaisLactantes();

   @Query(value = "SELECT animal FROM AnimalEntity animal WHERE (:idLote IS NULL OR animal.lote.idLote = :idLote)")
   List<AnimalEntity> findByIdLote(@Param("idLote") Long idLote);


   @Query("SELECT animal FROM AnimalEntity animal LEFT JOIN animal.lote lote WHERE lote.idLote != :idLote OR lote.idLote IS NULL")
   List<AnimalEntity> findAnimaisNaoContemNoLote(@Param("idLote") Long idLote);

   List<AnimalEntity> findByLoteIdLote(Long idLote);

   List<AnimalEntity> findByRacaIdRaca(Long idRaca);
}



