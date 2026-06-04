---
name: commit-descritivo
description: Prepare commits descritivos no padrão do projeto quando o usuário pedir commit ou quando uma alteração coerente estiver pronta para versionamento.
compatibility: opencode
metadata:
  project: partituras
  workflow: git
---

# commit-descritivo

Use esta skill antes de criar commits neste projeto.

## Regras principais

- Faça commit por alteração coerente.
- Use título curto, claro e parecido com Conventional Commits.
- Escreva corpo explicando objetivamente as mudanças principais.
- Use bullets no corpo quando eles deixarem a mensagem mais legível.
- Inclua coautoria quando a alteração tiver sido feita com apoio do Rocket/OpenCode.

## Fluxo

1. Revise `git status --short`.
2. Confira o diff dos arquivos que serão commitados.
3. Garanta que não há arquivos sensíveis ou locais, como `.env`, logs, `target/`, temporários ou dados locais.
4. Rode os testes relevantes quando houver alteração de código e isso for viável.
5. Crie uma mensagem com título e corpo coerentes com a mudança.

## Formato sugerido

```txt
tipo(escopo): resumo curto da mudança

- Descreve a primeira mudança principal
- Descreve a segunda mudança principal
- Registra validações feitas quando relevante

Co-authored-by: Rocket <rocket@noreply.local>
```

## Limites

- Não misture mudanças sem relação no mesmo commit.
- Não faça push se o usuário não pediu ou se a sessão atual não deixou isso combinado.
- Não crie subagents ou subtarefas para preparar commit.
