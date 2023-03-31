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
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl matriculaService;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public Matricula create(@Valid @RequestBody MatriculaForm form){
        return matriculaService.create(form);
    }
    @GetMapping
    public List<Matricula> getAll(@RequestParam(value="bairro", required = false)String bairro){
        return matriculaService.getAll(bairro);
    }

    @GetMapping("/{id}")
    public Matricula getOneMatricula(@PathVariable Long id){
        return matriculaService.get(id);
    }

    @DeleteMapping("/aluno/{idAluno}")
    public ResponseEntity<Object> deleteMatriculaAluno(@PathVariable Long idAluno){
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            try {
                matriculaService.deleteMatriculaAluno(aluno);
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada matrícula vinculada a esse aluno");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Matrícula deletada com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + idAluno);
    }

}
