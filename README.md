# 📚 Resenhita

Backend de um sistema de resenhas desenvolvido em Java com Spring Boot.

---

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 4.0.3**
  - Spring Web MVC
  - Spring Data JPA
  - Spring Security
- **PostgreSQL** — banco de dados relacional
- **JWT (Auth0 java-jwt 4.5.1)** — autenticação stateless
- **MapStruct 1.5.5** — mapeamento entre entidades e DTOs
- **Lombok** — redução de boilerplate
- **SpringDoc OpenAPI 3.0.2** — documentação automática da API (Swagger UI)
- **Maven** — gerenciamento de dependências e build

---

## 📋 Pré-requisitos

- Java 21+
- Maven 3.9+
- PostgreSQL rodando localmente ou via Docker

---

## ⚙️ Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/Attonic/resenhita.git
   cd resenhita
   ```

2. Configure o banco de dados no arquivo `src/main/resources/application.properties` (ou `application.yml`):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/resenhita
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Configure a chave secreta do JWT:
   ```properties
   api.security.token.secret=sua_chave_secreta
   ```

---

## ▶️ Como executar

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 📖 Documentação da API

Com a aplicação rodando, acesse o Swagger UI em:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 📁 Estrutura do Projeto

```
resenhita/
├── src/
│   ├── main/
│   │   ├── java/com/resenhita/   # Código fonte
│   │   └── resources/            # Configurações
│   └── test/                     # Testes
├── doc/                          # Documentação adicional
├── pom.xml                       # Dependências Maven
└── mvnw                          # Maven Wrapper
```

---

## 👥 Equipe

| Nome             | GitHub |
|------------------|--------|
| Antonio Cleison  | [@Attonic](https://github.com/Attonic) |
| Andrey Nicollas  |[@AndreyNicollas](https://github.com/AndreyNicollas)|

---

## 📄 Licença

Distribuído sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
