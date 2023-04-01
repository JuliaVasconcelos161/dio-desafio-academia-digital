package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;

import java.util.List;
import java.util.Optional;

public interface IMatriculaService {

  Matricula create(MatriculaForm form);

  Optional<Matricula> get(Long id);

  List<Matricula> getAll(String bairro);

  void delete(Long id);
  Matricula getMatriculaAluno(Aluno aluno);
  void deleteMatriculaAluno(Aluno aluno);

}
