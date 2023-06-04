package com.agromilk.br.repository;

import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.entity.LoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    @Query( value = "SELECT funcionario FROM FuncionarioEntity funcionario "
            + " WHERE 1=1 "
            + " AND ( :idFuncionario IS NULL OR funcionario.idFuncionario = :idFuncionario ) "
            + " AND ( :nomeFuncionario IS NULL OR funcionario.nomeFuncionario LIKE :nomeFuncionario ) "
            + " AND ( :cpf IS NULL OR funcionario.cpf LIKE :cpf ) "
            + " AND ( :dataNascimento IS NULL OR funcionario.dataNascimento = :dataNascimento ) "
            + " AND ( :endereco IS NULL OR funcionario.endereco LIKE :endereco ) "
            + " AND ( :telefone IS NULL OR funcionario.telefone LIKE :telefone ) ")
    List<FuncionarioEntity> findByFilter(Long idFuncionario,
                                         String nomeFuncionario,
                                         String cpf,
                                         LocalDate dataNascimento,
                                         String endereco,
                                         String telefone,
                                         Pageable pageable);

    Boolean existsByCpf(String cpf);
    @Query( value = "SELECT COUNT(funcionario.idFuncionario) FROM FuncionarioEntity  funcionario " )
    Long verificarQdtFuncionarios();
    @Query("SELECT f FROM FuncionarioEntity f WHERE LOWER(TRIM(f.nomeFuncionario)) = LOWER(TRIM(:nomeFuncionario))")
    Optional<FuncionarioEntity> findByUsername(String nomeFuncionario);

    Optional<FuncionarioEntity> findByEmail(String email);


}
