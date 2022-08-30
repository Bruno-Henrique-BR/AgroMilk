package com.agromilk.br.repository;

import com.agromilk.br.entity.LoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LoteRepository extends JpaRepository<LoteEntity, Long> {

}
