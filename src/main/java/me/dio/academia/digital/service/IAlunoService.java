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
  /**
   * Cria um Aluno e salva no banco de dados.
   * @param form - formulário referente aos dados para criação de um Aluno no banco de dados.
   * @return - Aluno recém-criado.
   */
  Aluno create(AlunoForm form);

  /**
   * Retorna um Aluno que está no banco de dados de acordo com seu Id.
   * @param id - id do Aluno que será exibido.
   * @return - Aluno de acordo com o Id fornecido.
   */
  Optional<Aluno> get(Long id);

  /**
   * Retorna todos os Alunos que estão no banco de dados.
   * @return - Uma lista os Alunos que estão salvas no DB.
   */
  List<Aluno> getAll(String dataDeNascimento);

  Page<Aluno> getAllPaginado(Pageable pageable);

  /**
   * Atualiza o Aluno.
   * @param id - id do Aluno que será atualizado.
   * @param formUpdate - formulário referente aos dados necessários para atualização do Aluno
   * no banco de dados.
   * @return - Aluno recém-atualizado.
   */
  Aluno update(Aluno aluno, AlunoUpdateForm formUpdate);

  /**
   * Deleta um Aluno específico.
   * @param id - id do Aluno que será removido.
   */
  void delete(Aluno aluno);

  List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id);
}
