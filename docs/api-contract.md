# Contrato inicial da API

## Objetivo

Definir o contrato inicial planejado para o CRUD de partituras.

Este documento descreve a intenção da API e não representa uma implementação final nem um contrato OpenAPI completo.

## Recurso principal

`/partituras`

## Entidade Partitura

Campos previstos:
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

## Enum de nível

Valores aceitos:
- `INICIANTE`
- `INTERMEDIARIO`
- `AVANCADO`

## Endpoints planejados

### Criar partitura
- método: `POST`
- rota: `/partituras`
- objetivo: cadastrar uma nova partitura

Request esperado:
```json
{
  "titulo": "Sonata em Dó",
  "compositor": "Mozart",
  "instrumento": "Piano",
  "nivel": "INTERMEDIARIO",
  "tom": "C",
  "arquivoUrl": "https://exemplo.com/partituras/sonata-em-do.pdf",
  "observacoes": "Versão para estudo"
}
```

Response esperado:
```json
{
  "id": 1,
  "titulo": "Sonata em Dó",
  "compositor": "Mozart",
  "instrumento": "Piano",
  "nivel": "INTERMEDIARIO",
  "tom": "C",
  "arquivoUrl": "https://exemplo.com/partituras/sonata-em-do.pdf",
  "observacoes": "Versão para estudo",
  "criadoEm": "2026-06-03T18:00:00Z",
  "atualizadoEm": "2026-06-03T18:00:00Z"
}
```

### Listar partituras
- método: `GET`
- rota: `/partituras`
- objetivo: listar partituras cadastradas

Response esperado:
```json
[
  {
    "id": 1,
    "titulo": "Sonata em Dó",
    "compositor": "Mozart",
    "instrumento": "Piano",
    "nivel": "INTERMEDIARIO",
    "tom": "C",
    "arquivoUrl": "https://exemplo.com/partituras/sonata-em-do.pdf",
    "observacoes": "Versão para estudo",
    "criadoEm": "2026-06-03T18:00:00Z",
    "atualizadoEm": "2026-06-03T18:00:00Z"
  }
]
```

### Detalhar partitura
- método: `GET`
- rota: `/partituras/{id}`
- objetivo: retornar uma partitura específica

### Atualizar partitura
- método: `PUT`
- rota: `/partituras/{id}`
- objetivo: atualizar os dados de uma partitura existente

Request esperado:
```json
{
  "titulo": "Sonata em Dó Maior",
  "compositor": "Mozart",
  "instrumento": "Piano",
  "nivel": "AVANCADO",
  "tom": "C",
  "arquivoUrl": "https://exemplo.com/partituras/sonata-em-do-maior.pdf",
  "observacoes": "Versão revisada"
}
```

### Remover partitura
- método: `DELETE`
- rota: `/partituras/{id}`
- objetivo: remover uma partitura cadastrada

## Respostas de erro planejadas

Exemplos de cenários esperados:
- `400 Bad Request` para payload inválido
- `404 Not Found` para partitura inexistente
- `409 Conflict` para conflitos de negócio, se surgirem regras futuras
- `500 Internal Server Error` para erros não tratados

## Observações

- os DTOs devem ser separados por operação
- entity não deve ser exposta diretamente pela API
- o contrato pode evoluir quando a implementação começar
