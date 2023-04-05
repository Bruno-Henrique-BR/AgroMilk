package com.agromilk.br.repository;

import com.agromilk.br.entity.LoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LoteRepository extends JpaRepository<LoteEntity, Long> {

    @Query( value = "SELECT lote FROM LoteEntity lote "
            + " WHERE 1=1 "
            + " AND ( :idLote IS NULL OR lote.idLote = :idLote ) "
            + " AND ( :nomeLote IS NULL OR lote.nomeLote LIKE :nomeLote ) "
            + " AND ( :descricao IS NULL OR lote.descricao LIKE :descricao ) ")
    List<LoteEntity> findByFilter(Long idLote,
                               String nomeLote,
                               String descricao,
                               Pageable pageable);
    List<LoteEntity> findByOrderByNomeLote();

    @Query( value = "SELECT COUNT(lote.idLote) FROM LoteEntity  lote " )
    Long verificarQdtLotes();

}
