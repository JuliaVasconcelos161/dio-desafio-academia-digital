package me.dio.academia.digital.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.dio.academia.digital.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    @Query(value = "SELECT * FROM tb_matricula m " +
            "INNER JOIN tb_alunos a ON m.aluno_id = a.id " +
            "WHERE a.bairro = :bairro", nativeQuery = true)
//    @Query("FROM Matricula m WHERE m.aluno.bairro = :bairro")
    List<Matricula> findAlunosMatriculadosBairro(String bairro);
//    List<Matricula> findByAlunoBairro(String bairro);

//    @Query(value = "DELETE FROM TB_MATRICULA M " +
//            "USING TB_ALUNOS A " +
//            "WHERE M.ALUNO_ID = A.ID " +
//            "AND A.ID = :id", nativeQuery = true)
//    void deleteMatriculaAluno(Long id);

    @Modifying
    @Query("DELETE FROM Matricula m WHERE m.aluno.id = :id")
    void deleteMatriculaAluno(Long id);

}
