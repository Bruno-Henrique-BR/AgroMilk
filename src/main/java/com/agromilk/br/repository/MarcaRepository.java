package com.agromilk.br.repository;

import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.entity.MarcaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    @Query( value = "SELECT marca FROM MarcaEntity marca "
            + " WHERE 1=1 "
            + " AND ( :idMarca IS NULL OR marca.idMarca = :idMarca ) "
            + " AND ( :nomeMarca IS NULL OR marca.nomeMarca LIKE :nomeMarca ) ")
    Page<MarcaEntity> findByFilter(Long idMarca,
                                  String nomeMarca,
                                  Pageable pageable);
}
