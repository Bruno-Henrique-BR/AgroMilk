package com.agromilk.br.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.repository.FuncionarioRepository;
import com.agromilk.br.security.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class DBService {

    @Autowired
    private FuncionarioRepository pessoaRepository;


    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {
        String sql = "";

        LocalDate dataNascimento = LocalDate.parse("2020-10-10", DateTimeFormatter.ISO_LOCAL_DATE);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        FuncionarioEntity tec1 = new FuncionarioEntity(null, "Bruno", "706.088.471-86", dataNascimento, "Rua dos Testes, 123", "(11) 99999-9999", "bruno@mail.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);
        sql += "INSERT INTO funcionario (nome_funcionario, cpf, data_nascimento, endereco, telefone, email, senha) VALUES (" +
                "'" + tec1.getNomeFuncionario() + "', " +
                "'" + tec1.getCpf() + "', " +
                "'" + tec1.getDataNascimento().format(dateFormatter) + "', " +
                "'" + tec1.getEndereco() + "', " +
                "'" + tec1.getTelefone() + "', " +
                "'" + tec1.getEmail() + "', " +
                "'" + tec1.getSenha() + "'" +
                ");\n";

        pessoaRepository.saveAll(Arrays.asList(tec1));
    }
}