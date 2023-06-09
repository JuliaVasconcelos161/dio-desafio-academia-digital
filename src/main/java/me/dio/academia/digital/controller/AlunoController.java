package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoServiceImpl service;

    @PostMapping
    public ResponseEntity<Aluno> create(@Valid @RequestBody AlunoForm form){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAluno(@PathVariable Long id){
        Optional<Aluno> alunoOptional = service.get(id);
        if(alunoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll(dataDeNascimento));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Aluno>> getAllAlunosPaginado(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPaginado(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoUpdateForm formUpdate){
        Optional<Aluno> alunoOptional = service.get(id);
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(service.update(aluno,formUpdate));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable Long id){
        Optional<Aluno> alunoOptional = service.get(id);
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            if(!aluno.getAvaliacoes().isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Exclua as avaliações do aluno primeiro.");
            } else if(service.isAlunoAssociadoMatricula(aluno)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Exclua a matrícula do aluno primeiro.");
            }
            service.delete(aluno);
            return ResponseEntity.status(HttpStatus.OK).body("Aluno excluído com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + id);
    }
}
