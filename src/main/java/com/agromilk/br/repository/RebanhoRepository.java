package com.agromilk.br.repository;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.RebanhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RebanhoRepository extends JpaRepository<RebanhoEntity, Long> {
}
