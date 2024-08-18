# API Java ACMK

## Descrição

Este projeto implementa uma API em Java para gerenciar informações de alunos em uma academia de karate. A API permite o cadastro de novos alunos, a recuperação de informações de alunos individuais e a listagem de todos os alunos. Utiliza o PostgreSQL como banco de dados e o Gson para manipulação de JSON.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação.
- **Maven**: Gerenciador de dependências e construção do projeto.
- **IntelliJ IDEA**: IDE utilizada para o desenvolvimento.
- **PostgreSQL**: Banco de dados relacional.
- **Gson**: Biblioteca para conversão de objetos Java para JSON e vice-versa.
- **Sun HttpServer**: Servidor HTTP embutido para manipulação de requisições.

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:
```
src
└── main
    └── java
        └── com
            └── acmk
                ├── Main.java
                ├── controller
                │   └── AlunoController.java
                ├── model
                │   └── Aluno.java
                ├── repository
                │   └── AlunoRepository.java
                └── service
                    └── AlunoService.java
```


- **`Main.java`**: Classe principal que inicia o servidor HTTP.
- **`controller/AlunoController.java`**: Controlador responsável por lidar com as requisições HTTP relacionadas a alunos.
- **`model/Aluno.java`**: Classe que representa o modelo de um aluno.
- **`repository/AlunoRepository.java`**: Classe responsável pelas operações de acesso ao banco de dados.
- **`service/AlunoService.java`**: Classe que contém a lógica de negócios para manipulação de dados dos alunos.

## Configuração

### Dependências

O projeto utiliza o Maven para gerenciar suas dependências, que estão listadas no arquivo `pom.xml`. As principais dependências são:

- **Lombok**: Facilita a criação de getters, setters e construtores.
- **Gson**: Para a conversão entre objetos Java e JSON.
- **PostgreSQL**: Driver JDBC para conexão com o banco de dados PostgreSQL.
- **Sun HttpServer**: Para a criação do servidor HTTP.

### Banco de Dados

1. **Conectar ao PostgreSQL**: Configure a conexão com o banco de dados PostgreSQL no arquivo `AlunoRepository.java`. Certifique-se de alterar as credenciais, se necessário.

2. **Criar a Tabela de Alunos**: Execute o script SQL para criar a tabela `alunos` no banco de dados PostgreSQL. A tabela deve ter a seguinte estrutura:

```sql
    CREATE TABLE alunos (
        id_aluno UUID PRIMARY KEY,
        primeiro_nome VARCHAR(100),
        sobrenome VARCHAR(100),
        data_nascimento DATE,
        genero VARCHAR(10),
        telefone VARCHAR(20),
        email VARCHAR(100),
        endereco VARCHAR(255),
        data_entrada DATE,
        cor_faixa VARCHAR(20)
    );
   ```

## Endpoints da API

- **POST /aluno**: Cadastra um novo aluno. O corpo da requisição deve ser um JSON com os dados do aluno.

  **Exemplo de JSON:**
  ```json
  {
    "primeiroNome": "João",
    "sobrenome": "Silva",
    "dataNascimento": "2000-01-01",
    "genero": "Masculino",
    "telefone": "123456789",
    "email": "joao.silva@example.com",
    "endereco": "Rua Exemplo, 123",
    "dataEntrada": "2024-01-01",
    "corFaixa": "Branca"
  }

- **GET /aluno?id_aluno={id}**: Recupera as informações de um aluno específico pelo ID. O ID deve ser um UUID.
- **GET /alunos**: Recupera a lista de todos os alunos.

## Executando o Projeto

1. **Compile e construa o projeto:**

    ```bash
    mvn clean install
    ```

2. **Inicie o servidor:**

    ```bash
    mvn exec:java -Dexec.mainClass="com.acmk.Main"
    ```

   O servidor será iniciado na porta 8000.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.


