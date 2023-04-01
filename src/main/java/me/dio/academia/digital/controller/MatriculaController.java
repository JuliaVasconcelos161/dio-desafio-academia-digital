package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl service;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody MatriculaForm form){
        Matricula matricula;
        try{
            matricula = service.create(form);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + form.getAlunoId());
        }
        return ResponseEntity.status(HttpStatus.OK).body(matricula);
    }
    @GetMapping
    public List<Matricula> getAll(@RequestParam(value="bairro", required = false)String bairro){
        return service.getAll(bairro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMatricula(@PathVariable Long id){
        Optional<Matricula> matriculaOptional = service.get(id);
        if(matriculaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(matriculaOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma matricula com o id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMatricula(@PathVariable Long id){
        Optional<Matricula> matriculaOptional = service.get(id);
        if(matriculaOptional.isPresent()){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Matrícula deletada com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma matricula com o id " + id);
    }

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<Object> getMatriculaAluno(@PathVariable Long idAluno){
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);
        Matricula matricula;
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            try {
                matricula = service.getMatriculaAluno(aluno);
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada matrícula vinculada a esse aluno");
            }
            return ResponseEntity.status(HttpStatus.OK).body(matricula);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + idAluno);
    }
    @DeleteMapping("/aluno/{idAluno}")
    public ResponseEntity<Object> deleteMatriculaAluno(@PathVariable Long idAluno){
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            try {
                service.deleteMatriculaAluno(aluno);
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada matrícula vinculada a esse aluno");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Matrícula deletada com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + idAluno);
    }

}
