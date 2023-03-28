package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {
    @Query(value = "DELETE FROM TB_AVALIACOES " +
            "WHERE ALUNO_ID = :aluno_id", nativeQuery = true)
    void deleteAvaliacoesAluno(Long aluno_id);
}
