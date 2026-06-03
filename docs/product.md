# Produto

## Visão geral

O produto será uma API REST para cadastro e gerenciamento de partituras.

O objetivo é permitir operações de CRUD sobre partituras, com foco inicial em organização de catálogo e manutenção de metadados musicais.

## Problema que o produto resolve

Centralizar o cadastro de partituras em uma API padronizada, permitindo que aplicações clientes consultem, criem, atualizem e removam registros de forma consistente.

## Entidade inicial

A primeira entidade do produto será `Partitura`.

Campos iniciais:
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

## Regras iniciais

- `id` será o identificador único da partitura
- `titulo` representa o nome da obra
- `compositor` representa o autor da obra
- `instrumento` identifica o instrumento principal relacionado à partitura
- `nivel` deve aceitar apenas os valores `INICIANTE`, `INTERMEDIARIO` e `AVANCADO`
- `arquivoUrl` representa a localização do arquivo da partitura
- `observacoes` é um campo livre para anotações adicionais
- `criadoEm` e `atualizadoEm` serão campos de auditoria

## Escopo inicial

O escopo inicial do produto contempla:
- cadastro de partitura
- listagem de partituras
- detalhamento de partitura
- atualização de partitura
- remoção de partitura

## Fora do escopo nesta fase

Neste momento não fazem parte do escopo:
- autenticação e autorização
- upload de arquivos
- versionamento de partituras
- busca avançada
- paginação detalhada
- observabilidade avançada
