# Partituras API

API para cadastro e gerenciamento de partituras.

## Status atual

Este repositório já possui a base inicial do projeto Spring Boot configurada, incluindo build com Maven, integração com PostgreSQL, Flyway e perfil de testes.

Nesta etapa foram definidos:
- documentação do produto
- diretrizes de arquitetura
- contrato inicial da API
- estratégia de testes
- decisões arquiteturais
- padrões de implementação para futuros agentes e contribuidores
- configuração inicial do ambiente local com Docker
- base técnica inicial da aplicação Spring Boot

Nenhuma funcionalidade de negócio do CRUD foi implementada ainda.

## Stack atual

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL Driver
- Flyway
- Lombok
- JUnit 5
- Mockito
- Maven
- Docker
- pgAdmin

## Dependências principais adicionadas

O projeto está configurado com as seguintes dependências principais:
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation`
- `flyway-core`
- `flyway-database-postgresql`
- `postgresql`
- `lombok`
- `spring-boot-starter-test`
- `mockito-junit-jupiter`

Para testes com o perfil `test`, também foi adicionada a dependência:
- `h2`

## Objetivo do produto

Disponibilizar uma API REST para cadastro, consulta, atualização e remoção de partituras.

## Entidade inicial

A entidade principal será `Partitura`, com os campos:
- `id`
- `titulo`
- `compositor`
- `instrumento`
- `nivel`
- `tom`
- `arquivoUrl`
- `observacoes`
- `criadoEm`
- `atualizadoEm`

Valores aceitos para `nivel`:
- `INICIANTE`
- `INTERMEDIARIO`
- `AVANCADO`

## Documentação

A pasta `docs/` concentra a documentação inicial do projeto:
- `docs/product.md`
- `docs/architecture.md`
- `docs/api-contract.md`
- `docs/test-strategy.md`
- `docs/decisions.md`

Além disso, o arquivo `AGENTS.md` define os padrões de implementação que devem ser seguidos antes de qualquer desenvolvimento.

## Estrutura arquitetural planejada

O projeto seguirá o padrão de monólito modular por domínio.

Cada domínio deverá concentrar seus próprios componentes, evitando uma separação puramente técnica em toda a aplicação.

## Configuração da aplicação

A aplicação foi configurada com:
- `application.yml` para ambiente padrão/local
- `application-test.yml` para testes
- Flyway apontando para `src/main/resources/db/migration`
- `spring.jpa.hibernate.ddl-auto=validate` no ambiente principal
- Flyway desabilitado no perfil de teste

Importante nesta fase:
- já existe migration inicial de domínio para a tabela `partituras`
- a base de persistência do domínio `partituras` já foi criada com entity, enum e repository
- ainda não existe controller

## Ambiente local com Docker

### Arquivos

O ambiente local usa os arquivos:
- `docker-compose.yml`
- `.env.example`

Crie seu arquivo `.env` local a partir do exemplo antes de subir os containers.

### Como subir o banco com Docker

```bash
docker compose --env-file .env up -d
```

Se ainda não existir um `.env`, copie o arquivo de exemplo e ajuste se necessário:

```bash
cp .env.example .env
```

No Windows PowerShell, você pode usar:

```powershell
Copy-Item .env.example .env
```

### Como parar os containers

```bash
docker compose --env-file .env down
```

Se quiser parar e remover também os volumes:

```bash
docker compose --env-file .env down -v
```

### Como acessar o pgAdmin

Após subir os containers, acesse:
- URL: `http://localhost:5050`
- email: `admin@admin.com`
- senha: `admin`

### Dados de conexão local do PostgreSQL

Use os seguintes dados para conexão local:
- host: `localhost`
- porta: `5432`
- banco: `partituras_db`
- usuário: `partituras_user`
- senha: `partituras_password`

No pgAdmin, ao cadastrar o servidor, use o nome do host `postgres` se a conexão for feita entre containers, ou `localhost` se estiver conectando a partir da máquina local.

## Como rodar o projeto

Antes de iniciar a aplicação, suba o PostgreSQL com Docker.

Depois, execute:

### Linux/macOS

```bash
./mvnw spring-boot:run
```

### Windows PowerShell

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação sobe por padrão na porta `8080`.

## Como rodar os testes

### Linux/macOS

```bash
./mvnw test
```

### Windows PowerShell

```powershell
.\mvnw.cmd test
```

Os testes usam o perfil `test`.

## Estratégia de migrations com Flyway

O projeto usará Flyway para versionamento do banco de dados.

As migrations deverão ficar em:
- `src/main/resources/db/migration`

Os arquivos deverão seguir o padrão de nomenclatura:
- `V1__descricao_da_migration.sql`
- `V2__descricao_da_migration.sql`

Diretrizes desta estratégia:
- alterações estruturais no banco devem ser feitas por migration
- não usar `spring.jpa.hibernate.ddl-auto=create` ou `spring.jpa.hibernate.ddl-auto=update` como estratégia principal
- em ambiente local, o banco deve ser criado pelo Docker e as tabelas devem ser criadas pelo Flyway
- a tabela `partituras` já é criada pela migration inicial `V1__criar_tabela_partituras.sql`
- a persistência usa `UUID` como chave primária e o enum `NivelPartitura` como texto

## Observação sobre `.env`

O arquivo `.env` é apenas local e não deve ser versionado.

O repositório deve manter somente o arquivo `.env.example` como referência.

## Próximos passos sugeridos

- criar a primeira migration real quando o domínio `Partitura` for implementado
- implementar domínio `partituras`
- criar testes unitários e de integração de negócio
- adicionar endpoints REST quando o contrato começar a ser materializado
