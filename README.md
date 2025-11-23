# 📘 Documentação da Aplicação - Sistema de Gerenciamento de Produtos

## 🛠️ Descrição Geral

Esta aplicação é um sistema de **CRUD de filmes** executado no terminal. Permite ao usuário **cadastrar**, **listar**, **buscar**, **atualizar** e **excluir** filmes de um banco de dados local (SQLite). A aplicação inclui **validações**.

---

## 📆 Recurso escolhido: **Produto**

### ✅ Atributos e suas propriedades

| Campo             | Tipo                  | Obrigatório | Observações                                                                             |
| ----------------- | --------------------- |-------------|-----------------------------------------------------------------------------------------|
| `idMovie`            | `Long`              | ✅ Sim       | Não pode ser vazio e deve ser único                                                     |
| `nameMovie`           | `String`              | ✅ Sim       | Não pode ser vazio                                                            |
| `director`       | `string`              | ❌ Não       | pode ser vazio                                                                          |
| `launchYear`         | `LocalDate` (YYYY-MM-DD)    | ❌ Não       | Se fornecida, deve ser uma data válida    |
| `duration` | `int`  | ❌ Não       | Deve ser um número inteiro maior ou igual a 0                                                  |

---

## 📌 Linguagem e Tecnologias Utilizadas

* **Java 21**
* **SQLite** 
* **Framework: Spring Boot 4.0.1** 
* ** Spring Data JPA **
* **Hibernate** 
* * **Maven** 

---

## 📥 Instalação e Configuração

### ✅ Pré-requisitos
* Git (opcional)

### 🔧 Passos para executar localmente

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/nome-repo.git
cd nome-repo

# 2. Instale as dependências
npm install

# 3. (Opcional) Exclua o banco de dados existente
rm database.db

# 4. Inicie a aplicação
npm start
```

---

## 🚀 Como Usar a Aplicação

A aplicação roda em modo interativo no terminal, oferecendo as seguintes opções:

### 1. Criar Filme

**Exemplo de entrada:**

```
Nome do Filme: Fabrica de Chocolate de Willian Wonka
Nome do Diretor: Jhony Deep
Categoria do Produto: Alimentos
launchYear: 2006-12-12
Duracao: 200
```

### 2. Listar Filmes

Exibe todos os filmes cadastrados.

### 3. Buscar Filmes por ID

```
ID do Produto: 1
```

### 4. Atualizar Filme

```
Nome do Filme: Fabrica de Chocolate de Willian Wonka
Nome do Diretor: Jhony Deep
Categoria do Produto: Alimentos
launchYear: 2006-12-12
Duracao: 190
```

### 5. Deletar Produto

```
ID do produto a ser deletado: 1
```

### 6. Sair

Encerra a aplicação com mensagem de despedida.


Esse arquivo cobre os seguintes cenários:

* ✅ Criação de filmes válidos
* ❌ Validação de dados obrigatórios e duplicidade
* 🔍 Busca de produtos por ID com tratamento de erro
* ✏️ Atualização com verificação de existência
* ❌ Tentativas de atualização e exclusão com ID inválido
* 🗑️ Exclusão com verificação de existência
* ✏️ Entre Outros

### Script SQL

```sql
CREATE TABLE IF NOT EXISTS movie (
    id_movie     INTEGER PRIMARY KEY AUTOINCREMENT,
    name_movie   TEXT NOT NULL,
    director     TEXT NOT NULL,
    launch_date  TEXT NOT NULL,
    duration     INTEGER NOT NULL
);
