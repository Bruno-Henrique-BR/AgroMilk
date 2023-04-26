package com.agromilk.br.repository;


import com.agromilk.br.entity.AnimalEntity;
import com.agromilk.br.entity.MovimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoRepository extends JpaRepository<MovimentoEntity, Long> {


    MovimentoEntity findFirstByAnimalOrderByDataEntradaDesc(AnimalEntity animal);
}

