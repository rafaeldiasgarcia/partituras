# Estratégia de testes

## Objetivo

Definir a estratégia inicial de testes para a API de partituras.

## Princípios

A base do projeto deve priorizar testes automatizados desde o início da implementação.

Todo service, mapper e validator deve ter teste unitário.

## Cobertura mínima esperada

### Services
Devem cobrir:
- cenário feliz
- erros esperados
- chamadas para dependências
- fluxo principal do caso de uso

### Mappers
Devem cobrir:
- conversão de request para entity
- conversão de entity para response
- preenchimento correto de campos

### Validators
Devem cobrir:
- regras de negócio válidas
- regras inválidas
- mensagens ou exceções esperadas

## Tipos de teste planejados

### Testes unitários
Serão a base inicial do projeto.

Foco em:
- services
- mappers
- validators

### Testes de integração
Devem ser adicionados em etapa posterior para validar:
- controllers
- repositories
- integração com banco de dados
- comportamento HTTP da API

## Ferramentas adotadas nesta base

- JUnit 5
- Mockito
- Spring Boot Test
- perfil `test` com H2 em memória

## Diretrizes

- testes devem ser claros e orientados a comportamento
- cada caso de uso deve ter cobertura mínima do fluxo principal e falhas relevantes
- evitar testes frágeis e excessivamente acoplados à implementação interna
- nomes de testes devem deixar explícito o cenário validado

## Estratégia para banco de dados e migrations

Quando os testes de integração com persistência forem implementados, o comportamento esperado do schema deve refletir a estratégia oficial do projeto:
- banco versionado com Flyway
- alterações estruturais aplicadas por migrations
- tabelas criadas a partir das migrations, e não por `ddl-auto=create` ou `update`

Isso ajuda a manter consistência entre ambiente local, testes e futura execução em outros ambientes.
