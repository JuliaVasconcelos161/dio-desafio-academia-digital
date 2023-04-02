<h1>dio-desafio-academia-digital</h1>

<p>Projeto idealizado pela [cami-la](https://www.linkedin.com/in/cami-la/ "cami-la"), instrutora da DIO, para o desafio "
Conhecendo Spring Data JPA na prática com Java". Alguns endpoints foram criados acompanhando a instrutora, outros foram implementados posteriormente.</p>

<h3>Endpoints:</h3>

<br/>

<h4>ALUNO</h4>

<h5>POST (/alunos)</h5>
Exemplo:
    {
        "nome": "joiu",
        "cpf": "713.676.460-22",
        "bairro": "Deserto",
        "dataDeNascimento": "23/09/2015"
    }
GET (/alunos/{idAluno})

GET (/alunos/page)

GET (/alunos?dataDeNascimento=10/09/2021)

PUT (/alunos/{idAluno})
Exemplo:
    {
        "nome": "Juai",
        "bairro": "Flores",
        "dataDeNascimento": "20/03/2023"
    }

DELETE (/alunos/{idAluno})

<br/>

MATRICULA

POST (/matriculas)
Exemplo:
    {
        "alunoId": 32
    }

GET (/matriculas/{idMatricula})

GET (/matriculas/aluno/{idAluno})

GET (/matriculas)

DELETE (/matriculas/{idMatricula})

DELETE (/matriculas/aluno/{idAluno})

<br/>

AVALIACÃO FÍSICA

POST (/avaliacoes)
Exemplo:
    {
        "alunoId":10,
        "peso":68,
        "altura":175
    }

GET (/avaliacoes)

GET (/avaliacoes/{idAvaliacao})

GET (/avaliacoes/aluno/{idAluno})

UPDATE (/avaliacoes/{idAvaliacao})
Exemplo:
    {
        "peso": 0,
        "altura": 130
    }

DELETE (/avaliacoes/aluno/{idAluno})

DELETE (/avaliacoes/aluno/{idAluno}/{idAvaliacao})

DELETE (/avaliacoes/aluno/{idAvaliacao})






