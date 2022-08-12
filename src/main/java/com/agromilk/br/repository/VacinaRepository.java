package com.agromilk.br.repository;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.VacinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<VacinaEntity, Long> {
}
