# 📚 Literalura

Este projeto trata-se do **challenge LiterAlura**, promovido pela Alura em parceria com o programa Oracle Next Education. A aplicação permite buscar e registrar livros utilizando a API Gutendex e armazenar as informações em um banco de dados PostgreSQL.

## 1. Configuração do Ambiente

- **Linguagem de Programação**: Java
- **Ferramenta de Gerenciamento de Projetos**: Maven
- **Versão do Spring Boot**: A versão utilizada no exemplo da aula.

### Dependências:
- **Spring Data JPA**: Facilita a persistência de dados.
- **Driver do PostgreSQL**: Para conectar ao banco de dados PostgreSQL.

## 2. Consumo da API Gutendex

O aplicativo utiliza a API Gutendex para:

- **Realizar a busca de livros por título**: O usuário pode inserir o título de um livro e a aplicação busca na API.
- **Obter informações do livro**: A API retorna dados como título, autor, idioma e número de downloads.

## 3. Persistência de Dados no Banco de Dados PostgreSQL

- **Criar Tabelas**: O aplicativo cria tabelas para armazenar dados dos livros, incluindo informações como título, autor, idioma e número de downloads.
- **Inserir Dados**: Os dados obtidos da API Gutendex são inseridos no banco de dados.

## 4. Interface do Usuário (Terminal)

O aplicativo oferece as seguintes opções no menu interativo:

1. **Buscar Livro pelo Título**: O usuário pode inserir o título de um livro, que será buscado na API Gutendex e registrado no banco de dados, caso não esteja previamente registrado.

2. **Listar Livros Registrados**: O usuário pode listar todos os livros armazenados no banco de dados.

3. **Listar Autores Registrados**: O usuário pode listar todos os autores dos livros armazenados.

4. **Listar Autores Vivos em um Determinado Ano**: O usuário pode inserir um ano e a aplicação lista todos os autores que estavam vivos naquele ano.

5. **Listar Livros em um Determinado Idioma**: O usuário pode inserir um idioma e a aplicação lista todos os livros disponíveis nesse idioma.

