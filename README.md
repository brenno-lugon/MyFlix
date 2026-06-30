# MyFlix

MyFlix é uma API REST desenvolvida com **Java + Spring Boot**, cujo objetivo é gerenciar usuários, filmes e o histórico
de filmes assistidos.

O projeto foi desenvolvido com foco em boas práticas de arquitetura de software, utilizando **Arquitetura Hexagonal (
Ports and Adapters)**, **MongoDB** para persistência dos dados e **Apache Kafka** para comunicação assíncrona baseada em
eventos.

---

# Tecnologias Utilizadas

- Java 26
- Spring Boot 4
- Spring Web
- Spring Data MongoDB
- Apache Kafka
- Docker
- Docker Compose
- Maven

---

# Arquitetura

O projeto utiliza Arquitetura Hexagonal, separando claramente as responsabilidades da aplicação.

```
Controller
        │
        ▼
Use Case
        │
        ▼
Service
        │
        ▼
Output Ports
        │
        ▼
MongoDB / Kafka
```

Quando um usuário registra um filme assistido, um evento é publicado no Kafka.

```
WatchedMovieService
        │
        ▼
MovieWatchedEvent
        │
        ▼
Kafka Producer
        │
        ▼
Topic movie-watched
        │
        ▼
Kafka Consumer
```

---

# Executando o Projeto

## Subir MongoDB

```bash
docker compose up -d mongodb
```

## Subir Kafka

```bash
docker compose up -d kafka
```

## Executar a aplicação

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

# Endpoints

## Usuários

### Criar usuário

**POST**

```
/user
```

Body

```json
{
  "name": "John Doe"
}
```

---

### Listar usuários

**GET**

```
/user
```

---

# Filmes

### Criar filme

**POST**

```
/movies
```

Body

```json
{
  "title": "Titanic",
  "description": "Um artista pobre e uma jovem rica se conhecem e se apaixonam na fatídica viagem inaugural do Titanic em 1912. Embora esteja noiva do arrogante herdeiro de uma siderúrgica, a jovem desafia sua família e amigos em busca do verdadeiro amor.",
  "genres": "Romance, Tragédia, Drama",
  "releaseYear": 1997
}
```

---

### Listar filmes

**GET**

```
/movies
```

---

### Buscar filme por ID

**GET**

```
/movies/{id}
```

Exemplo

```
GET /movies/684f0a5ec6f9913d34c8a912
```

---

### Pesquisar filmes por título

**GET**

```
/movies/search?title=titanic
```

---

### Atualizar filme

**PUT**

```
/movies/{id}
```

Body

```json
{
  "title": "Titanic",
  "description": "O navio afunda!",
  "genres": "Romance, Tragédia, Drama",
  "releaseYear": 1997
}
```

---

### Excluir filme

**DELETE**

```
/movies/{id}
```

---

# Filmes Assistidos

### Registrar filme assistido

**POST**

```
/watchedMovie
```

Body

```json
{
  "userId": "684f0a5ec6f9913d34c8a912",
  "movieId": "68501295cb2ff0eaf3c0011a"
}
```

Ao registrar um filme assistido:

- O registro é salvo no MongoDB
- Um evento é publicado no tópico Kafka `movie-watched`

---

### Buscar filmes assistidos por um usuário

**GET**

```
/watchedMovie/moviesByUserId?userId={userId}
```

Exemplo

```
GET /watchedMovie/moviesByUserId?userId=684f0a5ec6f9913d34c8a912
```

---

### Buscar usuários que assistiram determinado filme

**GET**

```
/watchedMovie/usersByMovieId?movieId={movieId}
```

Exemplo

```
GET /watchedMovie/usersByMovieId?movieId=68501295cb2ff0eaf3c0011a
```

---

# Kafka

## Tópico

```
movie-watched
```

## Evento publicado

```json
{
  "eventId": "4b739db9-7dc8-4d4c-bd49-056ef6df0c11",
  "occurredAt": "2026-06-29T20:30:00",
  "userId": "684f0a5ec6f9913d34c8a912",
  "userName": "John Doe",
  "movieId": "68501295cb2ff0eaf3c0011a",
  "movieTitle": "Titanic",
  "watchedAt": "2026-06-29T20:30:00"
}
```

O evento é consumido por um consumidor Kafka responsável por processar ações futuras (como um sistema de recomendações).

---

# Estrutura do Projeto

```
src
└── main
    └── java
        └── com.example.myflix
            ├── application
            │   └── service
            │
            ├── domain
            │   ├── event
            │   ├── model
            │   └── port
            │       ├── in
            │       └── out
            │
            ├── infrastructure
            │   ├── kafka
            │   ├── mongo
            │   │   ├── adapter
            │   │   ├── document
            │   │   ├── mapper
            │   │   └── repository
            │   └── web
            │       ├── controller
            │       ├── dto
            │       ├── exception
            │       └── mapper
            │
            └── MyflixApplication
```

---

# Funcionalidades

- Cadastro de usuários
- Listagem de usuários
- Cadastro de filmes
- Atualização de filmes
- Exclusão de filmes
- Busca de filmes por ID
- Pesquisa de filmes por título
- Registro de filmes assistidos
- Consulta dos filmes assistidos por um usuário
- Consulta dos usuários que assistiram determinado filme
- Persistência utilizando MongoDB
- Comunicação assíncrona utilizando Apache Kafka
- Arquitetura Hexagonal (Ports and Adapters)

---

# Melhorias Futuras

- Sistema de recomendação de filmes
- Spring Security + JWT
- Testes unitários
- Testes de integração
- OpenAPI / Swagger
- Dockerização completa da aplicação
- CI/CD com GitHub Actions

---

# Autor

Desenvolvido como projeto de estudos para aprofundamento em:

- Spring Boot
- Arquitetura Hexagonal
- NoSql (MongoDB)
- Apache Kafka
- Docker
- Desenvolvimento Backend Java
