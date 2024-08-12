# Projeto Generation


# Sobre o projeto


Projeto Blog pessoal uma aplicação backend feita com Java Spring Boot e MySQL, como parte do Bootcamp Desenvolvedor Fullstack Java/React da Generation Brasil.

Neste projeto, utilizei Java Spring Boot e Spring Security para criar uma aplicação segura e eficiente, respeitando os métodos HTTP. A aplicação foi configurada para ser segura e rápida através do 
uso de anotações do Spring. Implementamos métodos DTO para a criação e manipulação dos dados, utilizando modelos lambda e streams inline para acesso rápido e eficiente.

A aplicação é um CRUD para um blog, abrangendo temas, postagens e usuários cadastrados com e-mails válidos. Utilizamos JUnit para garantir a qualidade do código por meio de testes unitários, empregando um banco de dados local chamado H2 para os testes. O H2 é um banco de dados em memória que armazena dados apenas enquanto a aplicação está em execução.

Para o deploy, escolhi a plataforma Render. Como o MySQL é pago nessa plataforma, optei por usar o PostgreSQL, adicionando a dependência correspondente. Além disso, criei um container da 
aplicação utilizando Docker para facilitar o deploy e garantir a portabilidade.


## Desafio de conhecimento Proposto

![SpringSecurity](https://github.com/user-attachments/assets/667d6c48-60ae-438e-a296-4e768c64adfe)


# Tecnologias utilizadas
- Java
- Spring Boot
- Spring Security
- JUnit
- Postman
- MySQL
- PostgreSQL
- H2
- Deploy Render
- Docker

## Site em execução

https://blogpessoal-7hxh.onrender.com

ps* Online até dia 18/08/2024 pelo Render

# Como executar o projeto

Pré-requisitos: Java 17 e Spring Boot 3

## Clonar repositório HTTPS
```bash
git clone https://github.com/Guhfrontend/blogpessoal
```
## Clonar repositório SSH
```bash
git clone git@github.com:Guhfrontend/blogpessoal.git
```

*ps: para o caso de clonar, troque a porta do banco de dados do MySQL no application.properties para a porta padrão 3306, minha porta é 3307.

# Autor

Gustavo Ribeiro da Silva

https://www.linkedin.com/in/gustavo-r13/

