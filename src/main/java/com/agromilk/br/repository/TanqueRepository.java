package com.agromilk.br.repository;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.TanqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TanqueRepository extends JpaRepository<TanqueEntity, Long> {
}
