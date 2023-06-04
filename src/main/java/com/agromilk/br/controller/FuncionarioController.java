package com.agromilk.br.controller;

import com.agromilk.br.dto.FuncionarioDTO;
import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.exception.BadRequestException;
import com.agromilk.br.request.FuncionarioRequestDTO;
import com.agromilk.br.service.FuncionarioService;
import com.agromilk.br.util.Paginacao;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = {"${allowed.origin}"})
@RestController
@RequestMapping("agromilk/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FuncionarioEntity> cadastrarFuncionario(
            @RequestBody @Valid FuncionarioRequestDTO dto)
            throws NotFoundException, BadRequestException {

        FuncionarioEntity response = funcionarioService.salvar(dto);
        return new ResponseEntity<>(response, CREATED);
    }
    @PutMapping("/{idFuncionario}")
    public ResponseEntity<FuncionarioEntity> atualizarFuncionario(@PathVariable Long idFuncionario,
                                                    @RequestBody @Valid FuncionarioRequestDTO dto)
            throws Exception {
        FuncionarioEntity funcionarioDTO = this.funcionarioService.atualizar(dto, idFuncionario);
        return new ResponseEntity<>(funcionarioDTO, OK);
    }

    @GetMapping(value = "/{idFuncionario}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long idFuncionario) throws ObjectNotFoundException {
        FuncionarioEntity obj = funcionarioService.findById(idFuncionario);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioEntity>> listarTodos(
            @RequestParam(required = false) Long idFuncionario,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(required = false) String endereco,
            @RequestParam(required = false) String telefone,

            @PageableDefault(page = Paginacao.DEFAULT_PAGE_NUMBER,
                    value = Integer.MAX_VALUE) Pageable pageable) throws Exception {

        List<FuncionarioEntity> response = funcionarioService.listar(
                idFuncionario,
                nome,
                cpf,
                dataNascimento,
                endereco,
                telefone,
                pageable);
        return new ResponseEntity<>(response, OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<FuncionarioEntity> excluir(@PathVariable Long idFuncionario) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        FuncionarioEntity funcionario = funcionarioService.findById(idFuncionario);

        if (funcionario.getEmail().equals(username)) {
            throw new Exception("Você não tem permissão para excluir seu próprio usuário.");
        }

        funcionarioService.excluir(idFuncionario);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/usuario-logado")
    public ResponseEntity<String> getUsuarioLogado() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Aqui você pode usar o nome de usuário para buscar o funcionário correspondente
        FuncionarioEntity funcionario = funcionarioService.findByUsername(email);
        String nome = funcionario.getNomeFuncionario();

        return ResponseEntity.ok().body(nome);
    }


    @GetMapping("/qtsFuncionario")
    public Long qtsFuncionario(){
        return funcionarioService.funcionarios();
    }
}
