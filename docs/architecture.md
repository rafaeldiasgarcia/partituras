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
- coordenar um único caso de uso
- chamar validators
- interagir com repositories
- usar mappers
- definir o fluxo da funcionalidade

Para o domínio `partituras`, a organização atual dos casos de uso em services separados é:
- `CriarPartituraService`
- `ListarPartituraService`
- `DetalharPartituraService`
- `AtualizarPartituraService`
- `ExcluirPartituraService`

Nesta etapa, o domínio ainda não expõe controller. Os services já representam a camada de aplicação que será delegada pelos endpoints futuros.

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
- usar Flyway como estratégia de versionamento do banco de dados
- manter migrations em `src/main/resources/db/migration`
- aplicar alterações estruturais no banco exclusivamente por migration
- não usar `spring.jpa.hibernate.ddl-auto=create` ou `spring.jpa.hibernate.ddl-auto=update` como estratégia principal

## Estratégia de banco e migrations

O projeto usará Flyway para versionamento do banco de dados.

As migrations deverão ser mantidas em `src/main/resources/db/migration`.

O padrão de nomenclatura esperado para os arquivos será:
- `V1__descricao_da_migration.sql`
- `V2__descricao_da_migration.sql`

Diretrizes arquiteturais para persistência:
- toda alteração estrutural no banco deve ser feita por migration
- o banco local deve ser provisionado pelo Docker
- a criação de tabelas em ambiente local deve ser feita pelo Flyway
- `spring.jpa.hibernate.ddl-auto=create` e `spring.jpa.hibernate.ddl-auto=update` não devem ser usados como estratégia principal
- o domínio `partituras` já possui a base inicial de persistência com `Partitura`, `NivelPartitura` e `PartituraRepository`
- a entidade persistida usa `UUID` como identificador primário
- o campo `nivel` é persistido como texto a partir do enum `NivelPartitura`

## Observação sobre package base

O package base do projeto foi padronizado para `br.com.partiturasapi`.

Esse será o ponto de partida para a implementação dos domínios futuros.
