<h1>dio-desafio-academia-digital</h1>

<p>Projeto de um sistema de academia, na qual os alunos podem ter uma matrícula e várias avaliações de desempenho ao longo do tempo. Ele foi idealizado pela <a href="https://www.linkedin.com/in/cami-la/">cami-la</a>, instrutora da DIO, para o desafio "Conhecendo Spring Data JPA na prática com Java". Alguns endpoints foram criados acompanhando a instrutora, outros foram implementados posteriormente.</p>

<h3>Endpoints:</h3>

<h4>ALUNO</h4>

<p>POST (/alunos)</p>
<p>Exemplo:</p>
    <code>{
        "nome": "Larissa",
        "cpf": "713.676.460-22",
        "bairro": "Deserto",
        "dataDeNascimento": "23/09/2015"
    }</code>
<p>GET (/alunos/{idAluno})</p>

<p>GET (/alunos/page)</p>

<p>GET (/alunos?dataDeNascimento=10/09/2021)</p>

<p>PUT (/alunos/{idAluno})</p>
<p>Exemplo:</p>
    <code>{
        "nome": "João",
        "bairro": "Flores",
        "dataDeNascimento": "20/03/2023"
    }</code>

<p>DELETE (/alunos/{idAluno})</p>

<br/>

<h4>MATRICULA</h4>

<p>POST (/matriculas)</p>
<p>Exemplo:</p>
    <code>{
        "alunoId": 32
    }</code>

<p>GET (/matriculas/{idMatricula})</p>

<p>GET (/matriculas/aluno/{idAluno})</p>

<p>GET (/matriculas)

<p>DELETE (/matriculas/{idMatricula})</p>

<p>DELETE (/matriculas/aluno/{idAluno})</p>

<br/>

<h4>AVALIACÃO FÍSICA</h4>

<p>POST (/avaliacoes)</p>
<p>Exemplo:</p>
   <code> {
        "alunoId":10,
        "peso":68,
        "altura":175
    }</code>

<p>GET (/avaliacoes)</p>

<p>GET (/avaliacoes/{idAvaliacao})</p>

<p>GET (/avaliacoes/aluno/{idAluno})</p>

<p>UPDATE (/avaliacoes/{idAvaliacao})</p>
<p>Exemplo:</p>
    <code>{
        "peso": 0,
        "altura": 130
    }</code>

<p>DELETE (/avaliacoes/aluno/{idAluno})</p>

<p>DELETE (/avaliacoes/aluno/{idAluno}/{idAvaliacao})</p>

<p>DELETE (/avaliacoes/aluno/{idAvaliacao})</p>






