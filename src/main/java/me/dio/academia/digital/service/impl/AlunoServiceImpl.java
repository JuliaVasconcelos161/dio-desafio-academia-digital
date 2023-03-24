package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());
        return repository.save(aluno);
    }

    @Override
    public Optional<Aluno> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if(dataDeNascimento == null){
            return repository.findAll();
        }else {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return repository.findByDataDeNascimento(localDate);
        }

    }

    @Override
    public Page<Aluno> getAllPaginado(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {
       Optional<Aluno> alunoOptional = repository.findById(id);
       if(alunoOptional.isPresent()){
           Aluno aluno = alunoOptional.get();
           this.deleteMatriculaVinculada(aluno);
           if(!aluno.getAvaliacoes().isEmpty()){
               List<AvaliacaoFisica> avaliacoes = aluno.getAvaliacoes();
               avaliacoes.forEach(avaliacaoFisica -> avaliacaoFisicaRepository.delete(avaliacaoFisica));
           } else {
               repository.delete(aluno);
           }

       }else {
           throw new NoSuchElementException("O aluno com o id " + id + " não foi encontrado.");
       }

    }

    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
        Aluno aluno = repository.findById(id).get();
        return aluno.getAvaliacoes();
    }

    private void deleteMatriculaVinculada(Aluno aluno){
        List<Matricula> matriculas = matriculaRepository.findAll();
        for(Matricula m: matriculas){
            if(Objects.equals(m.getAluno().getId(), aluno.getId())){
                matriculaRepository.delete(m);
            }
        }

    }
}
