# Screen Sound Músicas

## Sobre o Projeto

Screen Sound Músicas é uma aplicação de linha de comando desenvolvida em Java com Spring Boot que permite gerenciar um catálogo de artistas e músicas. O sistema foi construído como parte de um estudo sobre Spring Data JPA, relacionamentos entre entidades e integração com APIs externas de inteligência artificial.

A aplicação demonstra na prática conceitos como mapeamento objeto-relacional (ORM) com Hibernate, operações CRUD, relacionamentos OneToMany e ManyToOne, consultas personalizadas com derived queries e integração com a API do Google Gemini para enriquecimento de dados.

## Arquitetura do Projeto

O projeto segue uma arquitetura de camadas simples:

---

# 📂 Estrutura do projeto

```text
screen-sound-musicas/
├── src/main/java/com/music/Musics/
│   ├── modelos/          # Entidades (Artistas, Musicas, TipoArtista)
│   ├── principal/        # Classe Principal com o menu
│   └── services/         # Repositórios e integração Gemini
├── src/main/resources/
│   └── application.properties  # Configurações do banco
└── pom.xml               # Dependências do Maven
```

---

# Tecnologias Utilizadas

- Java - Linguagem de programação principal
- Spring Boot 3.2.0 - Framework para configuração e execução da aplicação
- Spring Data JPA - Abstração para acesso a dados e ORM
- Hibernate - Implementação do JPA para mapeamento objeto-relacional
- PostgreSQL 15+ - Banco de dados relacional
- Google Gemini API - Serviço de inteligência artificial para pesquisa de artistas
- Maven - Gerenciador de dependências e build
- Terminal/Console - Interface com o usuário

---

# ⚙️ Como executar o projeto
## Pré-requisitos para Execução

- Java Jdk 17+
- PostgreSQL versão 15+
- Maven
- Chave de API do Google Gemini

## Clone o repositório

```bash
git clone https://github.com/lhborges-eng/Screen_Sound.git
```

## Acesse o diretório

```bash
cd screen_sound
```

## Configure o banco de dados

Edite o arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gerenciador_pedidos
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

## Execute a aplicação

```bash
mvn spring-boot:run
```
