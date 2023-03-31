package com.agromilk.br.repository;

import com.agromilk.br.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

   Boolean existsByRacaIdRaca(Long idRaca);

   Boolean existsByLoteIdLote(Long idLote);
   List<AnimalEntity> findByApelidoContainingIgnoreCase(String apelido);

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
   List<AnimalEntity> findByFilter(Long idAnimal,
                                   String codigo,
                                   String apelido,
                                   LocalDate dataNascimento,
                                   LocalDate dataCompra,
                                   String cor,
                                   String nomeLote,
                                   String nomeRaca,
                                   Boolean lactacao,
                                   Pageable pageable);
   @Query( value = "SELECT COUNT(animal.idAnimal) FROM AnimalEntity  animal " )
   Long verificarQdtAnimais();
   @Query( value = "SELECT COUNT(animal.idAnimal) FROM AnimalEntity  animal "
   + " WHERE animal.lactacao = true ")
   Long verificarQdtAnimaisLactacao();

   @Query( value = "SELECT COUNT(animal.idAnimal) FROM AnimalEntity  animal "
           + " WHERE animal.lactacao = false ")
   Long verificarQdtAnimaisSecas();

   @Query( value = "SELECT animal FROM AnimalEntity  animal "
           + " INNER JOIN OrdenhaEntity ordenha on animal.idAnimal = ordenha.animal"
           + " WHERE 1=1 "
           + " AND ( :quantidade IS NULL OR ordenha.quantidade < :quantidade ) "
           + " GROUP BY animal.idAnimal")
   Page<AnimalEntity> animaisOrdenhaAbaixoMedia(Double quantidade, Pageable pageable);
   @Query( value = "SELECT animal FROM AnimalEntity  animal "
           + " WHERE 1=1 "
           + " AND ( :idLote IS NULL OR animal.lote.idLote = :idLote ) ")
   List<AnimalEntity> findByIdLote(Long idLote);


}


