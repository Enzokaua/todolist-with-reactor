# TodoList API - com Spring WebFlux e Reactor

Este projeto implementa uma API RESTful para gerenciar uma lista de tarefas (TodoList) utilizando **Spring WebFlux** e o paradigma **Reactor**. O projeto é construído com **Java 21**, **Maven** como gerenciador de dependências, e **Lombok** para simplificar o código boilerplate.


## 📋 Funcionalidades

A API oferece endpoints para gerenciar tarefas (cards) com as seguintes operações:
- **GET `/getCard/{id}`**: Busca um card pelo ID.
- **POST `/postCard`**: Cria um novo card.
- **PUT `/attCard/{id}`**: Atualiza um card existente pelo ID.
- **DELETE `/dropCard/{id}`**: Deleta um card pelo ID.

### FluxResponse e FluxRequest
Esses objetos representam, respectivamente, a resposta e o payload esperado pela API:
- `FluxResponse`: Utilizado para retornar os dados de um card.
- `FluxRequest`: Representa o payload enviado pelo cliente para criar ou atualizar cards.

---

## 🛠️ Tecnologias Utilizadas
- **Java**: Versão 21.
- **Spring Framework**: Versão 3.3.3.
- **Spring WebFlux**: Versão 3.2.0.
- **Lombok**: Para reduzir código boilerplate.
- **MySQL**: Banco de dados para persistência.
- **Maven**: Gerenciador de dependências.

## 💻 Configuração do Banco de Dados
Configure o banco de dados no arquivo `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/todolist
    username: root
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
  sql:
    init:
      platform: mysql
```

## 🚀 Como Executar
1- Clone o repositório:
```bash
git clone https://github.com/enzokaua/todolist-with-reactor.git
cd todolist-webflux
```

2- Compile o projeto:
```bash
mvn clean install
```

3- Execute a aplicação::
```bash
mvn spring-boot:run
```
