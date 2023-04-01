package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {
    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) {
        Matricula matricula = new Matricula();
        Optional<Aluno> alunoOptional = alunoRepository.findById(form.getAlunoId());
        if(alunoOptional.isPresent()){
            matricula.setAluno(alunoOptional.get());
            return repository.save(matricula);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Optional<Matricula> get(Long id) {
        Optional<Matricula> matriculaOptional = repository.findById(id);
        return matriculaOptional;
    }

    @Override
    public List<Matricula> getAll(String bairro) {
        if(bairro == null){
            return repository.findAll();
        } else {
            return repository.findAlunosMatriculadosBairro(bairro);
        }

    }
    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteMatriculaAluno(Aluno aluno){
        if(this.isAlunoVinculadoMatricula(aluno)){
            repository.deleteMatriculaAluno(aluno.getId());
        }else{
            throw new IllegalArgumentException();
        }
    }

    public Matricula getMatriculaAluno(Aluno aluno){
        if(this.isAlunoVinculadoMatricula(aluno)){
            return repository.findMatriculaAluno(aluno.getId());
        }
        throw new IllegalArgumentException();
    }

    private Boolean isAlunoVinculadoMatricula(Aluno aluno){
        List<Matricula> matriculas = repository.findAll();
        if(!matriculas.isEmpty()){
            for(Matricula m: matriculas){
                if(Objects.equals(m.getAluno().getId(), aluno.getId())){
                    return true;
                }
            }
        }
        return false;
    }
}
