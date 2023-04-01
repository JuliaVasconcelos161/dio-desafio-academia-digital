package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaUpdateForm {
  @NotNull
  private double peso;
  @NotNull
  private double altura;
}
