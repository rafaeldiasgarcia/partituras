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

## Ferramentas esperadas

- JUnit 5
- Mockito
- Spring Boot Test

## Diretrizes

- testes devem ser claros e orientados a comportamento
- cada caso de uso deve ter cobertura mínima do fluxo principal e falhas relevantes
- evitar testes frágeis e excessivamente acoplados à implementação interna
- nomes de testes devem deixar explícito o cenário validado
