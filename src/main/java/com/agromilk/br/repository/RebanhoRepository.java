package com.agromilk.br.repository;

import com.agromilk.br.entity.RebanhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository

public interface RebanhoRepository extends JpaRepository<RebanhoEntity, Long> {
    @Query("SELECT r FROM RebanhoEntity r INNER JOIN AnimalEntity a ON r.idRebanho = a.rebanho where r.idRebanho =:idRebanho")

    Integer validadeDeleteRebanho(Long idRebanho);
}
