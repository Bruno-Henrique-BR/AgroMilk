package com.agromilk.br.repository;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.RebanhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RebanhoRepository extends JpaRepository<RebanhoEntity, Long> {

    @Query("SELECT TOP 1 ID_REBANHO FROM ANIMAL WHERE ID_REBANHO =:idRebanho ")
    Integer validadeDeleteRebanho(Long idRebanho);
}
