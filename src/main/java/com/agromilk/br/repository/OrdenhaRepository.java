package com.agromilk.br.repository;

import com.agromilk.br.dto.ProducaoLeiteDiariaDTO;
import com.agromilk.br.dto.ProducaoLeiteDiariaRelatorioDTO;
import com.agromilk.br.entity.OrdenhaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrdenhaRepository extends JpaRepository<OrdenhaEntity, Long> {

    @Modifying
    @Query(value = "DELETE FROM OrdenhaEntity ordenha "
            + "WHERE ordenha.animal.idAnimal = :idAnimal")
    void deleteByAnimalId(@Param("idAnimal") Long idAnimal);

    @Modifying
    @Query(value = "DELETE FROM OrdenhaEntity ordenha "
            + "WHERE ordenha.tanque.idTanque = :idTanque")
    void deleteByTanqueId(@Param("idTanque") Long idTanque);
    @Modifying
    @Query(value = "DELETE FROM OrdenhaEntity ordenha WHERE ordenha.idOrdenha = :idOrdenha")
    void deleteById(@Param("idOrdenha") Long idOrdenha);

    @Query(value = "SELECT ordenha FROM OrdenhaEntity ordenha "
            + " WHERE 1=1 "
            + " AND ( :idOrdenha IS NULL OR ordenha.idOrdenha = :idOrdenha ) "
            + " AND ( :data IS NULL OR ordenha.data = :data ) "
            + " AND ( :primeiraOrdenha IS NULL OR ordenha.primeiraOrdenha = :primeiraOrdenha ) "
            + " AND ( :segundaOrdenha IS NULL OR ordenha.segundaOrdenha = :segundaOrdenha ) "
            + " AND ( :idAnimal IS NULL OR ordenha.animal.idAnimal = :idAnimal ) "
            + " AND ( :idTanque IS NULL OR ordenha.tanque.idTanque = :idTanque ) ")
    List<OrdenhaEntity> findByFilter(Long idOrdenha,
                                     LocalDate data,
                                     Double primeiraOrdenha,
                                     Double segundaOrdenha,
                                     Long idAnimal,
                                     Long idTanque,
                                     Pageable pageable);


    @Query(value = "SELECT AVG(ordenha.primeiraOrdenha + ordenha.segundaOrdenha) FROM OrdenhaEntity ordenha")
    Double verificarMediaLitro();





    @Query(value = "SELECT AVG(ordenha.primeiraOrdenha + ordenha.segundaOrdenha) FROM OrdenhaEntity ordenha WHERE ordenha.animal.idAnimal = :idAnimal")
    Double mediaAnimal(@Param("idAnimal") Long idAnimal);


    @Query(value = "SELECT SUM(ordenha.primeiraOrdenha + ordenha.segundaOrdenha) FROM OrdenhaEntity ordenha")
    Double totalDeLeiteProduzido();

    @Query("SELECT CONCAT(YEAR(o.data), '-', TO_CHAR(o.data, 'TMMonth')) AS mes, SUM(o.primeiraOrdenha + o.segundaOrdenha) AS somaProducaoLeite " +
            "FROM OrdenhaEntity o " +
            "GROUP BY YEAR(o.data), TO_CHAR(o.data, 'TMMonth') " +
            "ORDER BY YEAR(o.data), MIN(o.data)")
    List<Object[]> obterSomaProducaoLeitePorMes();

    @Query("SELECT CONCAT(YEAR(o.data), '-', TO_CHAR(o.data, 'TMMonth')) AS mes, SUM(o.primeiraOrdenha + o.segundaOrdenha) AS somaProducaoLeite " +
            "FROM OrdenhaEntity o " +
            "WHERE o.animal.idAnimal = :idAnimal " +
            "GROUP BY YEAR(o.data), TO_CHAR(o.data, 'TMMonth') " +
            "ORDER BY YEAR(o.data), MIN(o.data)")
    List<Object[]> obterSomaProducaoLeitePorMesAnimal(@Param("idAnimal") Long idAnimal);

    @Query("SELECT CONCAT(EXTRACT(YEAR FROM o.data), '-', EXTRACT(WEEK FROM o.data)) AS semana, SUM(o.primeiraOrdenha + o.segundaOrdenha) AS somaProducaoLeite " +
            "FROM OrdenhaEntity o " +
            "GROUP BY EXTRACT(YEAR FROM o.data), EXTRACT(WEEK FROM o.data) " +
            "ORDER BY EXTRACT(YEAR FROM o.data), MIN(o.data)")
    List<Object[]> obterSomaProducaoLeitePorSemana();

    @Query("SELECT CONCAT(EXTRACT(YEAR FROM o.data), '-', EXTRACT(WEEK FROM o.data)) AS semana, SUM(o.primeiraOrdenha + o.segundaOrdenha) AS somaProducaoLeite " +
            "FROM OrdenhaEntity o " +
            "WHERE o.animal.idAnimal = :idAnimal " +
            "GROUP BY EXTRACT(YEAR FROM o.data), EXTRACT(WEEK FROM o.data) " +
            "ORDER BY EXTRACT(YEAR FROM o.data), MIN(o.data)")
    List<Object[]> obterSomaProducaoLeitePorSemanaAnimal(@Param("idAnimal") Long idAnimal);


    @Query("SELECT EXTRACT(DAY FROM o.data) as dataDia, COUNT(o) as quantidadeOrdenhas, SUM(o.primeiraOrdenha + o.segundaOrdenha) AS somaProducaoLeite " +
            "FROM OrdenhaEntity o " +
            "WHERE o.data >= :dataLimite " +
            "GROUP BY EXTRACT(DAY FROM o.data) " +
            "ORDER BY EXTRACT(DAY FROM o.data) DESC")
    List<Object[]> obterSomaProducaoLeiteUltimosSeteDias(@Param("dataLimite") LocalDate dataLimite);


    @Query("SELECT EXTRACT(DAY FROM o.data) as dataDia, COUNT(o) as quantidadeOrdenhas, SUM(o.primeiraOrdenha + o.segundaOrdenha) AS somaProducaoLeite " +
            "FROM OrdenhaEntity o " +
            "WHERE o.animal.idAnimal = :idAnimal " +
            "GROUP BY EXTRACT(DAY FROM o.data) " +
            "ORDER BY EXTRACT(DAY FROM o.data) DESC")
    List<Object[]> obterSomaProducaoLeiteUltimosSeteDiasAnimal(Pageable pageable, @Param("idAnimal") Long idAnimal);


    @Query("SELECT EXTRACT(DAY FROM o.data) as dataDia, COUNT(o) as quantidadeOrdenhas, SUM(o.primeiraOrdenha + o.segundaOrdenha) as somaProducaoLeite " +
            "FROM OrdenhaEntity o " +
            "WHERE o.data BETWEEN :dataInicial AND :dataFinal " +
            "GROUP BY EXTRACT(DAY FROM o.data) " +
            "ORDER BY EXTRACT(DAY FROM o.data) ASC")
    List<Object[]> obterOrdenhasPorPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);



}
