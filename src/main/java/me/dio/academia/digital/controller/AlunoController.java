package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public ResponseEntity<Object> getAllAlunos(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll(dataDeNascimento));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Aluno>> getAllAlunosPaginado(@PageableDefault(page = 0, size = 3, sort = "id") Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPaginado(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAluno(@PathVariable Long id){
        Optional<Aluno> alunoOptional = service.get(id);
        if(alunoOptional.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody AlunoForm form){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(form));
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id){
        return service.getAllAvaliacaoFisicaId(id);
    }

}
