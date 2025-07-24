# ShowHub Live

Backend em Quarkus para organizar shows, caronas e setlists em tempo real. Projeto feito passo a passo para estudar **Quarkus** (REST Reactive, Panache, Flyway, Kafka, Mutiny, Health, Metrics, Native Image etc).

## Stack

- **Java 21 + Quarkus 3.x**
- **Maven** (multi-módulo)
- **PostgreSQL** (Panache + Flyway)
- **Kafka** (SmallRye Reactive Messaging)
- **Micrometer + Prometheus** (metrics), SmallRye Health
- Docker Compose para infra local

## Módulos (até agora)

- `gateway-api`: endpoints REST (ex.: `/shows`), eventos Kafka, métricas custom.

## Como rodar

### 1. Infra
```bash
cd infra
docker compose up -d
