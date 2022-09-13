package com.agromilk.br.repository;

import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.entity.LaticinioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface LaticinioRepository extends JpaRepository<LaticinioEntity, Long> {
    @Query( value = "SELECT laticinio FROM LaticinioEntity laticinio "
            + " WHERE 1=1 "
            + " AND ( :idLaticinio IS NULL OR laticinio.idLaticinio = :idLaticinio ) "
            + " AND ( :razaoSocial IS NULL OR laticinio.razaoSocial LIKE :razaoSocial ) "
            + " AND ( :cnpj IS NULL OR laticinio.cnpj LIKE :cnpj ) "
            + " AND ( :endereco IS NULL OR laticinio.endereco LIKE :endereco ) "
            + " AND ( :telefone IS NULL OR laticinio.telefone LIKE :telefone ) ")
    Page<LaticinioEntity> findByFilter(Long idLaticinio,
                                         String razaoSocial,
                                         String cnpj,
                                         String endereco,
                                         String telefone,
                                         Pageable pageable);

    Boolean existsByCnpj(String cnpj);
}
