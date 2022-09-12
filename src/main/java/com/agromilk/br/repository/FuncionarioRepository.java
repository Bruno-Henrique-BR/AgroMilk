package com.agromilk.br.repository;

import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.entity.LoteEntity;
import com.agromilk.br.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    @Query( value = "SELECT funcionario FROM FuncionarioEntity funcionario "
            + " WHERE 1=1 "
            + " AND ( :idFuncionario IS NULL OR funcionario.idFuncionario = :idFuncionario ) "
            + " AND ( :nome IS NULL OR funcionario.nome LIKE :nome ) "
            + " AND ( :cpf IS NULL OR funcionario.cpf LIKE :cpf ) "
            + " AND ( :dataNascimento IS NULL OR funcionario.dataNascimento = :dataNascimento ) "
            + " AND ( :endereco IS NULL OR funcionario.endereco LIKE :endereco ) "
            + " AND ( :telefone IS NULL OR funcionario.telefone LIKE :telefone ) ")
    Page<FuncionarioEntity> findByFilter(Long idFuncionario,
                                  String nome,
                                  String cpf,
                                  LocalDate dataNascimento,
                                  String endereco,
                                  String telefone,
                                  Pageable pageable);

    Boolean existsByCpf(String cpf);


}
