package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;
    @Transactional
    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        Optional<Aluno> alunoOptional = alunoRepository.findById(form.getAlunoId());
        if(alunoOptional.isPresent()){
            avaliacaoFisica.setAluno(alunoOptional.get());
            avaliacaoFisica.setPeso(form.getPeso());
            avaliacaoFisica.setAltura(form.getAltura());
            return repository.save(avaliacaoFisica);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Optional<AvaliacaoFisica> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return repository.findAll();
    }

    @Override
    public AvaliacaoFisica update(AvaliacaoFisica avaliacaoFisica, AvaliacaoFisicaUpdateForm formUpdate) {
        avaliacaoFisica.setAltura(formUpdate.getAltura());
        avaliacaoFisica.setPeso(formUpdate.getPeso());
        return repository.save(avaliacaoFisica);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaAluno(Long idAluno) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);
        if(alunoOptional.isEmpty()){
            return null;
        }
        return alunoOptional.get().getAvaliacoes();
    }
    @Transactional
    @Override
    public void deleteAllAvaliacaoFisicaAluno(Long idAluno){
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);
        if(alunoOptional.isPresent()){
            List<AvaliacaoFisica> avaliacoesFisicas = this.getAllAvaliacaoFisicaAluno(idAluno);
            if(!avaliacoesFisicas.isEmpty()){
                avaliacoesFisicas.forEach(avaliacaoFisica -> repository.deleteById(avaliacaoFisica.getId()));
            } else {
                throw new IllegalArgumentException("Esse aluno não possui avaliações.");
            }
        } else{
            throw new IllegalArgumentException("Não foi encontrado nenhum aluno com o id " + idAluno);
        }
    }
    @Transactional
    @Override
    public void deleteOneAvaliacaoAluno(Long idAluno, Long idAvaliacao){
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);
        if(alunoOptional.isPresent()){
            List<AvaliacaoFisica> avaliacoesFisicas = this.getAllAvaliacaoFisicaAluno(idAluno);
            if(!avaliacoesFisicas.isEmpty()){
                boolean avaliacaoEncontrada = false;
                for(AvaliacaoFisica avaliacao: avaliacoesFisicas) {
                    if (Objects.equals(avaliacao.getId(), idAvaliacao)) {
                        avaliacaoEncontrada = true;
                        repository.deleteById(idAvaliacao);
                        break;
                    }
                }
                if(!avaliacaoEncontrada) {
                    throw new IllegalArgumentException("O idAvaliacao = " + idAvaliacao + " não foi encontrado para esse aluno.");
                }
            } else{
                throw new IllegalArgumentException("Esse aluno não possui avaliações.");
            }
        } else {
            throw new IllegalArgumentException("Não foi encontrado nenhum aluno com o id " + idAluno);
        }
    }

}
