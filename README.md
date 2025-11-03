## TodoList API - Gestão de Tarefas com Spring Boot

API REST para gerenciamento de tarefas (To-Do List), desenvolvida com Spring Boot e Java 17, aplicando boas práticas como DTOs, validações, ModelMapper, tratamento global de erros, paginação e filtros.

## Funcionalidades

✔ Criar, listar, atualizar e excluir tarefas

✔ Paginação e ordenação automática (page, size, sort)

✔ Filtro por título e status de conclusão (concluded = true/false)

✔ Combinação de busca por título + concluído

✔ DTOs para Request e Response com ModelMapper

✔ Validações com Bean Validation (@NotBlank, @Valid, etc.)

✔ Tratamento global de erros com @RestControllerAdvice

✔ Banco de dados H2 em memória (dev)

✔ Estrutura em camadas: Controller → Service → Repository

## Tecnologias Utilizadas

Categoria	Tecnologias
Linguagem	Java 17
Framework	Spring Boot (Web, Data JPA, Validation)
Banco de Dados	H2 Database (desenvolvimento)
ORM	Spring Data JPA
DTO & Mapper	ModelMapper
Build Tool	Maven
Versionamento	Git & GitHub

## Estrutura do Projeto

todolist/

├── config/         # Configurações (ModelMapper, etc.)

├── controller/     # Endpoints REST

├── dto/            # DTOs de Entrada e Saída

├── entity/         # Entidades JPA (Todo)

├── exception/      # Tratamento global de erros

├── repository/     # Repositórios (JpaRepository)

├── service/        # Regras de negócio

└── TodolistApplication.java
 
Como Executar/Pré-requisitos

Java 17+/
Maven

Rodando a aplicação
# Clonar o repositório
git clone https://github.com/usuario/todolist-api.git
cd todolist-api

# Executar
mvn spring-boot:run


A aplicação estará disponível em:
http://localhost:8080

### Endpoints Principais
Método	Endpoint	Descrição

GET	/todolist	Lista todas as tarefas (paginado)

GET	/todolist/{id}	Busca uma tarefa por ID

POST	/todolist	Cria uma nova tarefa

PUT	/todolist/{id}	Atualiza tarefa existente

DELETE	/todolist/{id}	Exclui uma tarefa

### Paginação e Ordenação

Parâmetro	Descrição	Padrão

page	Número da página (0-index)	0

size	Itens por página	10

sort	Campo e direção (asc/desc)	id,asc

### Exemplo:

GET /todolist?page=0&size=5&sort=title,desc

 Filtros de Busca

✔ Buscar por status de conclusão:

GET /todolist/concluded?concluded=true


✔ Buscar por título:

GET /todolist/search?title=estudar


✔ Buscar por título + concluído:

GET /todolist/search?title=spring&concluded=false

 Exemplo de Request (POST)
{
"title": "Estudar Spring Boot",
"description": "Revisar conceitos de API REST",
"concluded": false
}

 Exemplo de Response
{
"id": 1,
"title": "Estudar Spring Boot",
"description": "Revisar conceitos de API REST",
"concluded": false,
"createdAt": "2025-01-01T14:32:00"
}

 Tratamento de Erros (Exemplo)
{
"timestamp": "2025-01-01T15:00:00",
"status": 404,
"message": "Todo com ID 99 não encontrado"
}

## Melhorias Futuras

Autenticação com Spring Security + JWT

Documentação com Swagger / OpenAPI

Migração para PostgreSQL + Docker

Testes unitários e de integração (JUnit + Mockito)

## Autor

Guilherme Arthur

Desenvolvedor Back-end • Estudante de Tecnologia

 GitHub: https://github.com/Devtrur

 LinkedIn: https://www.linkedin.com/in/guilherme-arthur/
