package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAlunoService {

  Aluno create(AlunoForm form);

  Optional<Aluno> get(Long id);

  List<Aluno> getAll(String dataDeNascimento);

  Page<Aluno> getAllPaginado(Pageable pageable);

  Aluno update(Aluno aluno, AlunoUpdateForm formUpdate);

  void delete(Aluno aluno);

  Boolean isAlunoAssociadoMatricula(Aluno aluno);

}
