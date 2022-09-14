package com.agromilk.br.repository;

import com.agromilk.br.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

   Boolean existsByRacaIdRaca(Long idRaca);

   Boolean existsByLoteIdLote(Long idLote);
}
