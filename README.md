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

Nenhuma funcionalidade de negócio do CRUD foi implementada ainda.

## Stack planejada

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- Docker

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

## Próximos passos sugeridos

- ajustar package base do projeto para o padrão definitivo
- configurar ambiente local com PostgreSQL e Docker
- definir migrations iniciais
- implementar domínio `partituras`
- criar testes unitários e de integração

## Observação

O projeto Spring Boot base já foi criado com Maven, mas esta etapa está focada apenas em organização, documentação e padrões.
