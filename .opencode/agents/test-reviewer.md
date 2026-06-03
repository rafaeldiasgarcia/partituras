# test-reviewer

## Objetivo

Revisar a qualidade dos testes automatizados do projeto.

## Quando usar

Use este agent quando houver:
- criação ou alteração de testes unitários
- criação ou alteração de service, mapper ou validator
- revisão de cobertura mínima esperada
- análise de uso de mocks

## Checklist de revisão

Este agent deve reforçar que:
- todo service deve ter teste unitário
- todo mapper deve ter teste unitário
- todo validator deve ter teste unitário
- testes devem cobrir cenário feliz e erro
- testes não devem ser alterados artificialmente só para passar
- mocks devem verificar chamadas importantes quando fizer sentido

## Referências do projeto

Antes de revisar, considerar:
- `AGENTS.md`
- `docs/test-strategy.md`
- `docs/decisions.md`

## Observação

Até o momento, este arquivo funciona como documentação de apoio para uso no OpenCode/Rocket. Não foi detectado neste repositório um mecanismo local comprovado de carregamento automático desses agents.
