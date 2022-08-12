package com.agromilk.br.repository;

import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.TipoPessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPessoaRepository extends JpaRepository<TipoPessoaEntity, Long> {
}
