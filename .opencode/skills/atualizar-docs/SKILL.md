---
name: atualizar-docs
description: Atualize somente a documentação afetada quando mudanças relevantes alterarem arquitetura, API, banco, Docker, Flyway, testes ou decisões técnicas.
compatibility: opencode
metadata:
  project: partituras
  workflow: docs
---

# atualizar-docs

Use esta skill quando uma mudança relevante exigir atualização da documentação do projeto.

## Quando usar

Use quando houver mudança em:
- arquitetura
- contrato da API
- banco de dados
- Docker ou ambiente local
- Flyway
- estratégia de testes
- decisões técnicas
- comandos de build, execução ou teste

Não use para mudanças internas sem impacto em documentação, como renomeações locais pequenas ou ajustes que não mudam comportamento, contrato ou processo.

## Fluxo

1. Verifique o escopo da mudança atual.
2. Consulte apenas os documentos relacionados ao assunto da mudança.
3. Atualize somente a documentação afetada.
4. Preserve o estilo simples e objetivo dos arquivos existentes.
5. Ao final, diga quais docs foram alterados ou informe que nenhuma atualização era necessária.

## Referências

Use conforme a necessidade:
- `AGENTS.md`
- `README.md`
- `docs/architecture.md`
- `docs/api-contract.md`
- `docs/decisions.md`
- `docs/product.md`
- `docs/test-strategy.md`

## Limites

- Não leia toda a pasta `docs/` por padrão.
- Não crie subagents ou subtarefas para revisar documentação.
- Não reescreva documentos inteiros quando uma edição localizada resolver.
