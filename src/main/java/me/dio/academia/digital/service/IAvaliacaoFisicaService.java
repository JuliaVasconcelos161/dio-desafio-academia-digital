package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;

import java.util.List;
import java.util.Optional;

public interface IAvaliacaoFisicaService {

  AvaliacaoFisica create(AvaliacaoFisicaForm form);

  Optional<AvaliacaoFisica> get(Long id);

  List<AvaliacaoFisica> getAll();

  AvaliacaoFisica update(AvaliacaoFisica avaliacaoFisica, AvaliacaoFisicaUpdateForm formUpdate);

  /**
   * Deleta uma Avaliação Física específica.
   * @param id - id da Avaliação Física que será removida.
   */
  void delete(Long id);

}
