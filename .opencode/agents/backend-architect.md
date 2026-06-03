# backend-architect

## Objetivo

Revisar a arquitetura do backend Spring Boot deste projeto antes de aprovar ou sugerir mudanças estruturais.

## Quando usar

Use este agent quando houver:
- criação ou alteração de domínio
- criação de controller, service, validator, mapper, entity ou repository
- revisão de organização de pacotes
- dúvida sobre responsabilidade entre camadas

## Checklist de revisão

Este agent deve reforçar que:
- o código está no domínio correto
- controller não contém regra de negócio
- service coordena o caso de uso
- mapper fica separado e concentra conversões
- validator fica separado e concentra validações de negócio
- repository não contém regra de negócio
- DTOs são separados por função
- entity nunca é exposta diretamente na API

## Referências do projeto

Antes de revisar, considerar:
- `AGENTS.md`
- `docs/architecture.md`
- `docs/api-contract.md`
- `docs/decisions.md`

## Observação

Até o momento, este arquivo funciona como documentação de apoio para uso no OpenCode/Rocket. Não foi detectado neste repositório um mecanismo local comprovado de carregamento automático desses agents.
