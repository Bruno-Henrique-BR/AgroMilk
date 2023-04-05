package com.agromilk.br.repository;

import com.agromilk.br.entity.TanqueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TanqueRepository extends JpaRepository<TanqueEntity, Long> {
    @Query( value = "SELECT tanque FROM TanqueEntity tanque "
            + " WHERE 1=1 "
            + " AND ( :idTanque IS NULL OR tanque.idTanque = :idTanque ) "
            + " AND ( :descricao IS NULL OR tanque.descricao LIKE :descricao ) "
            + " AND ( :capacidade IS NULL OR tanque.capacidade = :capacidade ) "
            + " AND ( :modelo IS NULL OR tanque.modelo LIKE :modelo ) "
            + " AND ( :dataFabricacao IS NULL OR tanque.dataFabricacao = :dataFabricacao ) "
            + " AND ( :ativo IS NULL OR tanque.ativo = :ativo ) ")
    List<TanqueEntity> findByFilter(Long idTanque,
                                    String descricao,
                                    Double capacidade,
                                    String modelo,
                                    LocalDate dataFabricacao,
                                    Boolean ativo,
                                    Pageable pageable);

    @Query( value = "UPDATE TanqueEntity  tanque " +
            " SET tanque.quantidadeAtual = quantidadeAtual + :quantidade " +
            " WHERE tanque.idTanque = :idTanque " )
    @Modifying
    void enviarLeiteTanque(Long idTanque, Double quantidade);

    @Query( value = "UPDATE TanqueEntity  tanque " +
            " SET tanque.quantidadeAtual = quantidadeAtual - :quantidade " +
            " WHERE tanque.idTanque = :idTanque " )
    @Modifying
    void enviarLeiteColeta(Long idTanque, Double quantidade);

    @Query( value = "SELECT tanque.quantidadeAtual FROM TanqueEntity  tanque " +
            " WHERE tanque.idTanque = :idTanque " )
    Double verificarLeite(Long idTanque);

    @Query(value = "SELECT SUM(tanque.quantidadeAtual) FROM TanqueEntity tanque")
    Double somarQuantidadesAtuais();

    @Query( value = "SELECT COUNT(tanque.idTanque) FROM TanqueEntity  tanque " )
    Long verificarQdtTanques();


}
