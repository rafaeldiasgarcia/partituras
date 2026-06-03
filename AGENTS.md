# AGENTS.md - Padrões do projeto

Antes de implementar qualquer funcionalidade, leia este arquivo e a pasta `docs`.

## Objetivo

Este projeto será uma API de CRUD de partituras usando Spring Boot, PostgreSQL e Docker.

## Arquitetura

O backend deve seguir um monolito modular organizado por domínio.

Cada domínio deve concentrar seus próprios controllers, DTOs, mappers, services, validators, entities e repositories.

Código compartilhado deve ficar em `shared`.

Não crie pastas vazias apenas como promessa de arquitetura. Crie uma pasta somente quando houver código real nela.

## Estrutura esperada

Use uma estrutura parecida com esta:

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

## Controllers

Controllers não devem conter regra de negócio.

O controller deve apenas:
- receber a requisição HTTP
- aplicar anotações de rota e status
- delegar o fluxo para service
- retornar DTOs de resposta

## Services

Services devem coordenar o caso de uso.

Responsabilidades esperadas:
- orquestrar validações
- chamar repositories
- usar mappers
- definir o fluxo da funcionalidade

## Validators

Validators devem concentrar validações de negócio e regras que não pertencem ao controller.

## Mappers

Mappers devem concentrar conversões entre entity e DTO.

Prefira métodos com prefixo `to`, por exemplo:
- `toEntity`
- `toCriarPartituraResponse`
- `toListarPartituraResponse`
- `toDetalharPartituraResponse`
- `toAtualizarPartituraResponse`

Não faça conversão manual em controller ou service quando existir mapper.

## DTOs

Não use entity como request ou response da API.

Cada funcionalidade deve ter seus próprios DTOs.

Exemplos:
- `CriarPartituraRequest`
- `CriarPartituraResponse`
- `ListarPartituraResponse`
- `DetalharPartituraResponse`
- `AtualizarPartituraRequest`
- `AtualizarPartituraResponse`

Não reutilize DTO só porque os campos parecem iguais hoje.

## Repositories

Repositories devem cuidar apenas de acesso a dados.

Não coloque regra de negócio em repository.

## Testes

Todo service, mapper e validator deve ter teste unitário.

Testes devem cobrir:
- cenário feliz
- validações
- erros esperados
- chamadas para dependências quando fizer sentido

## Commits, push e coautoria

A partir desta etapa, cada alteração coerente pode ser commitada e enviada para o repositório.

Regras permanentes:
- sempre que concluir uma alteração coerente, pode criar um commit
- pode fazer push da branch atual após o commit
- não precisa juntar mudanças diferentes no mesmo commit
- cada commit deve representar um contexto real, como `docs`, `docker`, `config`, `database`, `test`, `feature`, `fix` ou `refactor`
- antes de commitar, confira se não existem arquivos sensíveis ou locais sendo versionados, como `.env`, logs, `target/`, arquivos temporários ou dados locais
- se alterar código, rode os testes relevantes antes do commit quando possível
- se alterar apenas documentação, não precisa rodar a suíte inteira, mas valide se a mudança está coerente

### Padrão de mensagem de commit

As mensagens devem seguir um padrão parecido com Conventional Commits.

Diretrizes:
- o título do commit deve ser curto e claro
- o corpo do commit deve explicar objetivamente o que foi alterado
- quando fizer sentido, use bullets no corpo para detalhar as principais mudanças

Exemplo:

```txt
chore(docs): documenta padrão de commits e coautoria

- Adiciona regra para commits por alteração coerente
- Define padrão descritivo para mensagens de commit
- Documenta uso de coautoria para alterações feitas com Rocket/OpenCode
- Reforça cuidado com arquivos locais e sensíveis

Co-authored-by: Rocket <rocket@noreply.local>
```

### Coautoria Rocket/OpenCode

Quando a alteração for feita com apoio do Rocket/OpenCode, incluir no commit:

```txt
Co-authored-by: Rocket <rocket@noreply.local>
```

Se no futuro existir um email oficial para Rocket/OpenCode, esse valor poderá substituir o endereço temporário acima.

## Regra final

Antes de criar código novo, confira se a implementação respeita:
- código no domínio correto
- controller sem lógica
- service coordenando caso de uso
- validator com validações de negócio
- mapper com conversões
- repository apenas com acesso a banco
- DTOs separados por função
- testes unitários para services, mappers e validators
- padrão de commit, push e coautoria documentado neste arquivo
