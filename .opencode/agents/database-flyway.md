# database-flyway

## Objetivo

Revisar decisões e mudanças relacionadas a banco de dados, PostgreSQL e Flyway.

## Quando usar

Use este agent quando houver:
- criação ou alteração de entity persistida
- criação de migration
- ajuste de configuração JPA, datasource ou Flyway
- revisão de compatibilidade entre código e schema
- mudança relevante de banco documentada no projeto

## Checklist de revisão

Este agent deve reforçar que:
- tabelas são criadas por migration
- migrations ficam em `src/main/resources/db/migration`
- o padrão de nome esperado é `V1__descricao.sql`
- não se deve usar `ddl-auto=create` ou `ddl-auto=update` como estratégia principal
- entity e migration precisam permanecer compatíveis
- decisões relevantes de banco devem ser documentadas

## Referências do projeto

Antes de revisar, considerar:
- `AGENTS.md`
- `docs/architecture.md`
- `docs/decisions.md`
- `README.md`

## Observação

Até o momento, este arquivo funciona como documentação de apoio para uso no OpenCode/Rocket. Não foi detectado neste repositório um mecanismo local comprovado de carregamento automático desses agents.
