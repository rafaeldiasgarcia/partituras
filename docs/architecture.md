# Arquitetura

## Estilo arquitetural

O backend deve seguir um monólito modular organizado por domínio.

A ideia é manter alta coesão dentro de cada domínio e baixo acoplamento entre módulos.

## Organização por domínio

Cada domínio deve concentrar seus próprios componentes, como:
- controller
- dto
- mapper
- service
- validator
- entity
- repository

Código compartilhado deve ficar em `shared`.

## Estrutura de referência

```text
src/main/java/br/com/partiturasapi/
|-- partituras/
|   |-- controller/
|   |-- dto/
|   |-- mapper/
|   |-- service/
|   |-- validator/
|   |   `-- service/
|   |-- entity/
|   `-- repository/
`-- shared/
    |-- config/
    `-- exception/
```

## Responsabilidades por camada

### Controller
- receber requisições HTTP
- aplicar anotações de rota
- delegar para service
- retornar DTOs

Controller não deve conter regra de negócio.

### Service
- coordenar o caso de uso
- chamar validators
- interagir com repositories
- usar mappers
- definir o fluxo da funcionalidade

### Validator
- aplicar validações de negócio
- centralizar regras que não pertencem ao controller

### Mapper
- converter request em entity
- converter entity em response
- evitar conversões manuais espalhadas pelo código

### Repository
- cuidar apenas do acesso a dados
- não conter regra de negócio

## Convenções importantes

- não usar entity como contrato externo da API
- criar DTOs específicos por operação
- não criar pastas vazias sem código real
- manter código compartilhado apenas em `shared`
- priorizar nomes explícitos e orientados ao caso de uso

## Observação sobre package base

O projeto atual foi gerado com um package base inicial do Spring Initializr.

Antes da implementação do domínio, o package base deve ser ajustado para o padrão definitivo adotado pelo projeto.
