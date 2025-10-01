# ProjectToLearn - Sistema de Gerenciamento de Academia

## 📋 Descrição

Sistema de gerenciamento de alunos e professores de academia desenvolvido com o objetivo de aprofundar conhecimentos em **Spring Boot** e **Java**. A aplicação implementa um CRUD completo com autenticação JWT, relacionamento entre entidades e validações robustas.

### Principais Funcionalidades

- 🔐 **Autenticação e Autorização**: Sistema de login e registro com JWT (JSON Web Token)
- 👥 **Gerenciamento de Alunos**: Cadastro completo com validações de CPF, e-mail, telefone e planos
- 👨‍🏫 **Gerenciamento de Professores**: Cadastro com validação de CREF e graduação
- 🔗 **Relacionamento**: Vinculação de alunos a professores (ManyToOne)
- 📄 **Documentação**: API documentada com Swagger/OpenAPI
- ✅ **Testes Unitários**: Cobertura de testes com JUnit e Mockito

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.5**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Validation
  - Spring DevTools
- **Spring Security** com JWT (Auth0 java-jwt 4.5.0)
- **PostgreSQL** (Produção)
- **H2 Database** (Desenvolvimento e Testes)
- **Hibernate** - Persistência de dados
- **Lombok** - Redução de boilerplate
- **MapStruct 1.6.0** - Mapeamento de DTOs
- **SpringDoc OpenAPI 2.8.13** - Documentação da API
- **Maven** - Gerenciamento de dependências
- **JUnit 5** e **Mockito** - Testes unitários

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java JDK 17** ou superior
- **Maven 3.9.11** ou superior
- **PostgreSQL** (opcional, para ambiente de produção)
- **Git** (para clonar o repositório)

## 🚀 Instalação e Execução

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/ProjectToLearn.git
cd ProjectToLearn
```

### 2. Configure as variáveis de ambiente (opcional)

Para ambiente de produção, configure as seguintes variáveis:

```bash
export DB_HOST=seu_host_postgres
export DB_NAME=seu_usuario
export DB_PASSWORD=sua_senha
export JWT_SECRET=sua_chave_secreta
```

### 3. Execute a aplicação

#### Usando Maven Wrapper (Recomendado)

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

#### Usando Maven instalado

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

Após iniciar a aplicação, acesse a documentação interativa do Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

## 🔑 Autenticação

### 1. Registrar um novo usuário

```http
POST /auth/register
Content-Type: application/json

{
  "login": "usuario@email.com",
  "password": "senha123"
}
```

### 2. Fazer login

```http
POST /auth/login
Content-Type: application/json

{
  "login": "usuario@email.com",
  "password": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3. Usar o token nas requisições

Adicione o token no header de todas as requisições protegidas:

```
Authorization: Bearer seu_token_jwt_aqui
```

## 📋 Endpoints Principais

### Alunos (Members)

- `POST /member` - Cadastrar novo aluno
- `GET /member` - Listar todos os alunos (paginado)
- `GET /member/{id}` - Buscar aluno por ID
- `PUT /member/{id}` - Atualizar dados do aluno
- `DELETE /member/{id}` - Remover aluno
- `PATCH /member/{id_member}` - Desvincular professor do aluno

### Professores (Teachers)

- `POST /teacher` - Cadastrar novo professor
- `GET /teacher` - Listar todos os professores (paginado)
- `GET /teacher/{id}` - Buscar professor por ID
- `PUT /teacher/{id}` - Atualizar dados do professor
- `DELETE /teacher/{id}` - Remover professor
- `PATCH /teacher/{id_teacher}/{id_member}` - Vincular professor a um aluno

## 🧪 Executar Testes

```bash
./mvnw test
```

ou

```bash
mvn test
```

## 📊 Estrutura do Projeto

```
src/
├── main/
│   ├── java/crud/ProjectToLearn/
│   │   ├── application/          # Camada de aplicação (Services e DTOs)
│   │   │   ├── Helper/           # Mappers (MapStruct)
│   │   │   ├── Member/           # Serviços de Aluno (Command/Query)
│   │   │   ├── Teacher/          # Serviços de Professor
│   │   │   └── User/             # Serviços de Autenticação
│   │   ├── controller/           # Controllers REST
│   │   ├── domain/               # Entidades e Exceções
│   │   │   ├── Entity/           # Entidades JPA
│   │   │   ├── Enums/            # Enumerações
│   │   │   └── Exceptions/       # Tratamento de exceções
│   │   └── infrastructure/       # Camada de infraestrutura
│   │       ├── repository/       # Repositórios JPA
│   │       ├── security/         # Configurações de segurança
│   │       └── springdoc/        # Configurações do Swagger
│   └── resources/
│       ├── application.properties          # Configuração desenvolvimento (H2)
│       └── application-prod.properties     # Configuração produção (PostgreSQL)
└── test/                         # Testes unitários
```

## 🔧 Configuração de Perfis

A aplicação possui dois perfis:

### Desenvolvimento (padrão)
- Usa banco H2 em memória
- SQL visível no console
- Acesso ao console H2: `http://localhost:8080/h2-console`

### Produção
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```
- Usa PostgreSQL
- SQL não é exibido

## 📝 Validações Implementadas

### Aluno (Member)
- ✅ E-mail válido e único
- ✅ CPF válido (formato brasileiro) com 11 dígitos
- ✅ Nome entre 8 e 30 caracteres
- ✅ Telefone com 11 dígitos (DDD + número)
- ✅ Data de nascimento no passado
- ✅ Plano obrigatório (MONTHLY, QUARTERLY, SEMESTER, ANNUAL)

### Professor (Teacher)
- ✅ Nome entre 8 e 30 caracteres
- ✅ CREF no formato: `CREF 002887-G/SC`
- ✅ Graduação

### Usuário (User)
- ✅ E-mail válido
- ✅ Senha mínima de 5 caracteres

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais e está livre para uso e modificação.

## ✨ Autor

Desenvolvido como projeto de estudo de Spring Boot e Java

---

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório!

