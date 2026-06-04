# Decisões arquiteturais

## Objetivo

Registrar decisões iniciais do projeto para orientar a implementação futura.

## Decisão 1 - Stack principal

O projeto utilizará:
- Spring Boot
- Maven
- PostgreSQL
- Docker

### Motivo

Essa combinação oferece produtividade, ecossistema maduro e facilidade de execução local.

## Decisão 2 - Estilo arquitetural

O backend seguirá um monólito modular por domínio.

### Motivo

Esse modelo favorece organização por contexto de negócio, reduz dispersão de código e facilita evolução incremental.

## Decisão 3 - Organização interna dos domínios

Cada domínio deverá concentrar controller, dto, mapper, service, validator, entity e repository.

### Motivo

A proximidade entre componentes do mesmo domínio melhora coesão e manutenção.

## Decisão 4 - Contratos externos com DTOs

A API não deve expor entities diretamente.

### Motivo

DTOs específicos por operação reduzem acoplamento e permitem evolução segura do contrato.

## Decisão 5 - Conversões centralizadas em mappers

As conversões entre entity e DTO devem ficar em mappers dedicados.

### Motivo

Isso evita duplicação de conversão em controllers e services.

## Decisão 6 - Regras de negócio em validators e services

Validators e services serão responsáveis pelas regras de negócio e coordenação dos casos de uso.

### Motivo

Essa separação mantém controllers enxutos e repositories focados em persistência.

## Decisão 7 - Estratégia de versionamento do banco

O projeto usará Flyway para controlar e versionar as migrations do banco de dados.

As migrations deverão ficar em `src/main/resources/db/migration` e seguir o padrão:
- `V1__descricao_da_migration.sql`
- `V2__descricao_da_migration.sql`

Alterações estruturais no banco deverão ser feitas por migration.

`spring.jpa.hibernate.ddl-auto=create` e `spring.jpa.hibernate.ddl-auto=update` não devem ser usados como estratégia principal.

Em ambiente local, o banco será criado pelo Docker e as tabelas serão criadas pelo Flyway.

A base inicial de persistência do domínio `partituras` foi implementada com a entidade `Partitura`, o enum `NivelPartitura`, o `PartituraRepository` e a migration `V1__criar_tabela_partituras.sql`.

### Motivo

Essa abordagem melhora rastreabilidade, previsibilidade de evolução do schema e consistência entre ambientes.

## Decisão 8 - Padrão permanente de commits, push e coautoria

A partir desta etapa, cada alteração coerente poderá ser commitada e enviada para o repositório.

Diretrizes adotadas:
- commits não precisam agrupar mudanças sem relação entre si
- cada commit deve representar um contexto real, como `docs`, `docker`, `config`, `database`, `test`, `feature`, `fix` ou `refactor`
- as mensagens devem seguir um padrão parecido com Conventional Commits
- o título deve ser curto e claro
- o corpo deve explicar objetivamente o que foi alterado
- quando fizer sentido, o corpo pode usar bullets para destacar as principais mudanças
- após o commit, é permitido fazer push da branch atual
- antes de commitar, deve-se revisar se não há arquivos sensíveis ou locais sendo versionados, como `.env`, logs, `target/`, arquivos temporários ou dados locais
- quando houver alteração de código, os testes relevantes devem ser executados antes do commit quando possível
- quando houver apenas alteração de documentação, não é necessário rodar a suíte inteira, mas a coerência da mudança deve ser validada

Para commits feitos com apoio do Rocket/OpenCode, deve ser usada a coautoria:

`Co-authored-by: Rocket <rocket@noreply.local>`

Se no futuro existir um email oficial para Rocket/OpenCode, esse valor poderá substituir o endereço temporário atual.

### Motivo

Essa decisão melhora rastreabilidade, reduz acúmulo artificial de mudanças, incentiva histórico mais legível e formaliza a coautoria das alterações feitas com apoio do Rocket/OpenCode.

## Decisão 9 - Base técnica inicial da aplicação

Nesta fase o projeto passa a manter também a base técnica inicial da aplicação Spring Boot, incluindo build, configuração de ambiente, perfil de testes e integração com PostgreSQL e Flyway.

### Motivo

A intenção é alinhar estrutura, convenções e infraestrutura mínima antes da implementação do CRUD.

## Decisão 10 - Organização padrão de agents e skills para OpenCode/Rocket

O projeto adota a organização padrão do OpenCode para regras, agents e skills.

Estrutura adotada:
- `AGENTS.md` mantém as regras do projeto
- `opencode.json` mantém a configuração do projeto
- `.opencode/agents/*.md` mantém agents customizados em Markdown com frontmatter YAML
- `.opencode/skills/<nome>/SKILL.md` mantém skills com `name` e `description`

Agents registrados:
- `@backend-architect`, para revisão arquitetural Spring Boot
- `@database-flyway`, para revisão de PostgreSQL, JPA e Flyway
- `@test-reviewer`, para revisão de testes automatizados

Skills registradas:
- `atualizar-docs`, para atualização localizada de documentação
- `commit-descritivo`, para preparação de commits descritivos

Para evitar criação automática e recursiva de subagents, `opencode.json` bloqueia `permission.task` por padrão e libera apenas `backend-architect`, `database-flyway` e `test-reviewer` com `ask`. Os agents customizados também bloqueiam `task`, `edit` e `bash`, usam `mode: subagent` e têm limite de passos.

### Motivo

Essa decisão mantém agents e skills versionados no padrão do OpenCode, mas evita fan-out automático, subtarefas recursivas e exploração ampla em tarefas simples.

## Decisão 11 - Modo de trabalho padrão para tarefas pequenas e médias

Para tarefas simples, pequenas ou médias, o trabalho deve ser feito diretamente no código, sem criar subagents, subtarefas paralelas ou explorações extensas.

Diretrizes adotadas:
- não criar subagents, subtarefas paralelas ou explorações extensas para tarefas simples
- para mudanças pequenas ou médias, trabalhar diretamente no código
- só fazer exploração ampla quando a tarefa for realmente ambígua, grande ou arriscada
- antes de implementar, fazer no máximo um plano curto com 3 a 6 passos
- não reabrir análise de arquitetura inteira a cada prompt
- ler somente os arquivos necessários para a tarefa atual
- carregar skills somente quando a descrição da skill for diretamente relevante
- usar agents somente quando chamados manualmente, explicitamente solicitados ou aprovados pelo usuário
- manter commits descritivos com coautoria do Rocket

### Motivo

Essa decisão reduz overhead operacional, evita análise excessiva em mudanças objetivas, melhora foco na tarefa atual e mantém o histórico de commits claro e rastreável.
