package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {
    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody AvaliacaoFisicaForm form){
        try {
            AvaliacaoFisica avaliacaoFisica = service.create(form);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum aluno com o id " + form.getAlunoId());
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.create(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAvaliacao(@PathVariable Long id){
        Optional<AvaliacaoFisica> avaliacaoFisicaOptional = service.get(id);
        if(avaliacaoFisicaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(avaliacaoFisicaOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma avaliação com o id " + id);
    }

    @GetMapping
    public ResponseEntity<Object> getAllAvaliacoes(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

}
