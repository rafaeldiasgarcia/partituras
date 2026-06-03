# Partituras API

API para cadastro e gerenciamento de partituras.

## Status atual

Este repositório está na fase inicial de organização do projeto.

Nesta etapa foram definidos:
- documentação do produto
- diretrizes de arquitetura
- contrato inicial da API
- estratégia de testes
- decisões arquiteturais
- padrões de implementação para futuros agentes e contribuidores
- configuração inicial do ambiente local com Docker

Nenhuma funcionalidade de negócio do CRUD foi implementada ainda.

## Stack planejada

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- Flyway
- Docker
- pgAdmin

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
- a primeira migration real será criada quando a entidade `Partitura` for implementada

## Ambiente local com Docker

### Arquivos

O ambiente local usa os arquivos:
- `docker-compose.yml`
- `.env.example`

Crie seu arquivo `.env` local a partir do exemplo antes de subir os containers.

### Como subir os containers

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

## Observação sobre `.env`

O arquivo `.env` é apenas local e não deve ser versionado.

O repositório deve manter somente o arquivo `.env.example` como referência.

## Próximos passos sugeridos

- ajustar package base do projeto para o padrão definitivo
- configurar propriedades da aplicação para conexão com PostgreSQL
- definir migrations iniciais
- implementar domínio `partituras`
- criar testes unitários e de integração

## Padrão de commits, push e coautoria

A partir de agora, cada alteração coerente do projeto pode gerar um commit próprio e pode ser enviada com push para a branch atual.

Diretrizes resumidas:
- cada commit deve representar um contexto real, como `docs`, `docker`, `config`, `database`, `test`, `feature`, `fix` ou `refactor`
- as mensagens devem seguir um padrão parecido com Conventional Commits
- o título deve ser curto e claro
- o corpo deve explicar objetivamente o que foi alterado
- quando fizer sentido, use bullets no corpo do commit
- antes de commitar, revise se não há arquivos sensíveis ou locais sendo versionados
- alterações de código devem rodar testes relevantes quando possível
- alterações apenas de documentação devem ser validadas quanto à coerência, sem exigir a suíte inteira

Para alterações feitas com apoio do Rocket/OpenCode, use por enquanto:

```txt
Co-authored-by: Rocket <rocket@noreply.local>
```

Se no futuro existir um email oficial para Rocket/OpenCode, esse valor poderá ser substituído.

## Observação

O projeto Spring Boot base já foi criado com Maven, mas esta etapa continua focada apenas em organização, documentação e ambiente local. A API ainda não foi implementada.
