package com.agromilk.br.repository;

import com.agromilk.br.entity.RebanhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository

public interface RebanhoRepository extends JpaRepository<RebanhoEntity, Long> {

}
