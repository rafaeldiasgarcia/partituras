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

## Decisão 7 - Escopo desta etapa

Nesta fase serão mantidos apenas organização inicial, documentação e padrões do projeto.

### Motivo

A intenção é alinhar estrutura e convenções antes da implementação do CRUD.
