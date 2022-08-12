package com.agromilk.br.repository;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.OrdenhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenhaRepository extends JpaRepository<OrdenhaEntity, Long> {
}
