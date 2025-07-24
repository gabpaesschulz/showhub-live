# ShowHub Live

Backend em **Quarkus 3.x + Java 21** para organizar shows, caronas e setlists em tempo real. Projeto construído em micropassos para servir de base de estudos/portfólio: REST Reactive, Panache/Flyway, Kafka (Reactive Messaging), Mutiny, Health, Metrics, Native Image etc.

## Stack

- **Java 21 + Quarkus 3.x**
- **Maven** (multi-módulo)
- **PostgreSQL** (Panache + Flyway)
- **Kafka** (SmallRye Reactive Messaging)
- **Micrometer + Prometheus** (`/q/metrics`)
- **SmallRye Health** (`/q/health`)
- Docker Compose para infra local

## Módulos (até agora)

- `gateway-api`: endpoints REST (`/shows`), eventos Kafka, métricas custom.

## Como rodar

### 1. Infra
```bash
cd infra
docker compose up -d
```

### 2. App (dev mode)
```bash
mvn -pl gateway-api quarkus:dev
```

- Health: `http://localhost:8080/q/health`
- Metrics: `http://localhost:8080/q/metrics`
- OpenAPI/Swagger: `http://localhost:8080/q/swagger-ui`

### 3. Teste rápido
```bash
curl -X POST http://localhost:8080/shows \
  -H "Content-Type: application/json" \
  -d '{"name":"System of a Down","city":"Curitiba","date":"2025-05-06"}'
```

Isso retorna o show criado e publica um evento no Kafka (`show-created`). O consumer imprime no console.

## Próximos passos

- Scheduler para lembretes pré-show
- Qute para gerar PDF/Checklist de viagem
- Security (Keycloak + OIDC/JWT)
- Observabilidade completa (Grafana/Prometheus/Tempo/Jaeger)
- Build nativo & CI/CD (GitHub Actions)
