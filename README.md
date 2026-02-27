# Investment Asset App

REST API for managing investment assets, portfolios, and accounting reports. Provides CRUD operations for assets, banks, asset types and sub-types, categories, and integrates with external quotation services.

## Features

- **Asset management** — Full CRUD for investment assets (quantity, average price, quotation, value, category, bank, type).
- **Master data** — CRUD for Banks, Asset Types, Asset Sub-Types, and Asset Categories.
- **Accounting reports** — Endpoint to retrieve raw accounting report data for assets.
- **Quotation integration** — Optional integration with an external quotation API (e.g. `http://localhost:8084/app/quotation`) to fetch current prices by asset name.
- **API documentation** — OpenAPI 3 (SpringDoc) with Swagger UI.
- **Monitoring** — Spring Boot Actuator and HAL Explorer for discovery and health checks.
- **Validation** — Bean Validation on request bodies; centralized exception handling with structured error responses.

## Tech stack

| Technology        | Purpose                    |
|-------------------|----------------------------|
| Java 17+          | Runtime                    |
| Spring Boot 3.3.x | Web, REST, dependency mgmt |
| Spring Data JPA   | Persistence                |
| PostgreSQL        | Database                   |
| Lombok            | Boilerplate reduction      |
| SpringDoc OpenAPI | API docs & Swagger UI      |
| Spring Actuator   | Health & metrics           |
| Spring Data REST  | HAL / REST explorer        |
| Gradle            | Build                      |

## Prerequisites

- **JDK 17** or higher
- **PostgreSQL** (e.g. 12+) with a database created (e.g. `finance`)
- (Optional) External **quotation API** running if you use quotation integration

## Configuration

Main settings live in `application.yaml`:

| Property                    | Description                          | Example                    |
|----------------------------|--------------------------------------|----------------------------|
| `spring.datasource.url`     | PostgreSQL JDBC URL                  | `jdbc:postgresql://localhost:5432/finance` |
| `spring.datasource.username`| Database user                        | `usuario`                  |
| `spring.datasource.password`| Database password                    | *(set via env or config)*  |
| `spring.jpa.hibernate.ddl-auto` | Schema strategy                  | `update`                   |
| `spring.profiles.active`   | Active profile                       | `dev`                      |
| `springdoc.api-docs.enabled` | Enable OpenAPI JSON                | `true`                     |
| `springdoc.swagger-ui.path`| Swagger UI path                      | `asset/swagger-ui.html`    |

Do not commit real credentials. Prefer environment variables or a separate profile (e.g. `application-dev.yaml` in `.gitignore`).

## Running the project

**From the project root:**

```bash
./gradlew bootRun
```

**Build (no run):**

```bash
./gradlew clean build
```

**Tests:**

```bash
./gradlew test
```

The API is available at `http://localhost:8080` (default port).

## API overview

All asset-related endpoints are under the `/asset` base path. Below are the main resources and their endpoints (new and existing):

| Resource                 | Base path                       | Methods & Notes |
|--------------------------|----------------------------------|-----------------|
| Assets                   | `/asset`                         | GET `/` (list), GET `/{id}`, GET `/query?asset=NAME`, POST `/`, PUT `/{id}`, DELETE `/{id}` |
| Banks                    | `/asset/bank`                    | GET `/` (list), GET `/{id}`, GET `/query?bank=NAME`, POST `/`, DELETE `/{id}` |
| Asset types (AssetType)  | `/asset/asset-type`              | GET `/` (list), GET `/{id}`, GET `/query?name=NAME`, POST `/`, DELETE `/{id}` |
| Asset sub-types (AssetSubType) | `/asset/asset-sub-type`    | GET `/` (list), GET `/{id}`, GET `/query?name=NAME`, GET `/by-asset-type/{assetTypeId}`, POST `/`, DELETE `/{id}` |
| Categories               | `/asset/category`                | CRUD (similar pattern) |
| Asset contributions      | `/asset/contribution`            | GET `/` (list), GET `/{id}`, GET `/asset/{assetId}`, POST `/`, PUT `/{id}`, DELETE `/{id}` |
| Investments contribution | `/asset/investments-contribution`| GET `/` (list), GET `/{id}`, POST `/`, PUT `/{id}`, DELETE `/{id}` |
| Reports                  | `/asset/report`                  | `GET /asset/report/accounting` — accounting report data |

Responses use standard HTTP status codes (200, 201, 204, 400, 404, 500). Not found and validation errors return JSON bodies (e.g. `AssetErrorDetails`).

## Endpoints detail and example payloads

Below are concise descriptions and example cURL commands for the main endpoints that were added/updated.

1) Banks (Master data)

- Base path: `http://localhost:8080/asset/bank`
- Model: `Bank` — fields: `id`, `bank` (name)

Examples:

- List banks
  curl -X GET http://localhost:8080/asset/bank

- Get bank by id
  curl -X GET http://localhost:8080/asset/bank/1

- Find by name
  curl -X GET "http://localhost:8080/asset/bank/query?bank=MyBank"

- Create bank
  curl -X POST http://localhost:8080/asset/bank \
    -H "Content-Type: application/json" \
    -d '{"bank":"MyBank"}'

- Delete bank
  curl -X DELETE http://localhost:8080/asset/bank/1

2) AssetType (Master data)

- Base path: `http://localhost:8080/asset/asset-type`
- Model: `AssetType` — fields: `id`, `name`, `subTypes` (list)

Examples:

- Create asset type
  curl -X POST http://localhost:8080/asset/asset-type \
    -H "Content-Type: application/json" \
    -d '{"name":"Stocks"}'

- Find by name
  curl -X GET "http://localhost:8080/asset/asset-type/query?name=Stocks"

3) AssetSubType (Master data)

- Base path: `http://localhost:8080/asset/asset-sub-type`
- Model: `AssetSubType` — fields: `id`, `name`, `assetType` (object with id)

Examples:

- Create sub-type (attach to an existing asset type by id)
  curl -X POST http://localhost:8080/asset/asset-sub-type \
    -H "Content-Type: application/json" \
    -d '{"name":"Small Caps","assetType":{"id":1}}'

- List sub-types for an asset type
  curl -X GET http://localhost:8080/asset/asset-sub-type/by-asset-type/1

4) Asset (main resource)

- Base path: `http://localhost:8080/asset`
- Model: `Asset` — salient fields (not exhaustive):
  - `id`
  - `asset` (name)
  - `quantity` (BigDecimal)
  - `averagePrice` (BigDecimal)
  - `quotation` (BigDecimal)
  - `assetCategory` (object with `id`)
  - `bank` (object with `id`)  <-- include bank as requested
  - `type` (object with `id`)  <-- AssetType reference
  - other optional fields: `monthlyContribution`, `valuePreviousMonth`, etc.

Create example (note: relationships are sent as nested objects with `id`):

- Create asset (example with bank and type included)
  curl -X POST http://localhost:8080/asset \
    -H "Content-Type: application/json" \
    -d '{
      "asset":"ABC",
      "quantity":10,
      "averagePrice":100.00,
      "quotation":105.00,
      "bank": {"id": 1},
      "type": {"id": 2},
      "assetCategory": {"id": 3}
    }'

- Update asset
  curl -X PUT http://localhost:8080/asset/1 \
    -H "Content-Type: application/json" \
    -d '{"asset":"ABC Updated","quantity":12}'

- Query by name
  curl -X GET "http://localhost:8080/asset/query?asset=ABC"

5) Asset Contribution (per-asset contributions history)

- Base path: `http://localhost:8080/asset/contribution`
- Model: `AssetContribution` — fields: `id`, `asset` (object with id), `contributionDate` (yyyy-MM-dd), `value`

Examples:

- Create contribution for an asset
  curl -X POST http://localhost:8080/asset/contribution \
    -H "Content-Type: application/json" \
    -d '{"asset":{"id":1},"contributionDate":"2026-02-26","value":100.00}'

- List all contributions
  curl -X GET http://localhost:8080/asset/contribution

- List contributions by asset id
  curl -X GET http://localhost:8080/asset/contribution/asset/1

- Update contribution
  curl -X PUT http://localhost:8080/asset/contribution/1 \
    -H "Content-Type: application/json" \
    -d '{"asset":{"id":1},"contributionDate":"2026-02-26","value":120.00}'

6) Investments Contribution (global contributions)

- Base path: `http://localhost:8080/asset/investments-contribution`
- Model: `InvestmentsContribution` — fields: `id`, `contributionDate` (yyyy-MM-dd), `value`

Examples:

- Create investments contribution
  curl -X POST http://localhost:8080/asset/investments-contribution \
    -H "Content-Type: application/json" \
    -d '{"contributionDate":"2026-02-26","value":500.00}'

- List all
  curl -X GET http://localhost:8080/asset/investments-contribution

- Update
  curl -X PUT http://localhost:8080/asset/investments-contribution/1 \
    -H "Content-Type: application/json" \
    -d '{"contributionDate":"2026-02-26","value":600.00}'

- Delete
  curl -X DELETE http://localhost:8080/asset/investments-contribution/1

## Documentation and tools

- **Swagger UI:** [http://localhost:8080/asset/swagger-ui.html](http://localhost:8080/asset/swagger-ui.html) (or `/asset/swagger-ui/index.html`)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **Actuator:** [http://localhost:8080/actuator](http://localhost:8080/actuator)
- **HAL Explorer:** [http://localhost:8080/explorer/index.html](http://localhost:8080/explorer/index.html) (base URI: `http://localhost:8080/asset`)

## Project structure

```
src/main/java/com/app/financial/investmentassetapp/
├── ConsultaAtivoApiApplication.java   # Spring Boot entry point
├── config/                            # CORS and other configuration
├── controller/                        # REST controllers (Asset, Bank, AssetType, AssetSubType, AssetCategory, AssetReport)
├── excpetion/                         # AssetNotFoundException, HandlerError, AssetErrorDetails
├── external/                          # Quotation API client (QuotationExternal, DTOs)
├── model/                             # JPA entities (Asset, Bank, AssetType, AssetSubType, AssetCategory, AccountingReportAsset)
├── repository/                        # JPA repositories (interfaces + *RepositoryImpl)
├── service/                           # Business logic (asset, report, masters)
└── utils/                             # Utilities
```

Layered flow: **Controller → Service → Repository Impl → JPA Repository (interface)**. Master data (Bank, AssetType, AssetSubType, AssetCategory) follow the same pattern.

## Docker

A multi-stage `Dockerfile` is provided: Gradle build then run with a JRE image.

**Build image:**

```bash
docker build -t investment-asset-app .
```

**Run (example; pass DB config via env or mount config):**

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/finance \
  -e SPRING_DATASOURCE_USERNAME=usuario \
  -e SPRING_DATASOURCE_PASSWORD=yourpassword \
  investment-asset-app
```

Adjust host (e.g. `host.docker.internal` or your DB host) and credentials as needed.

## External integration (quotations)

The app can consume an external quotation API to resolve current prices by asset name. The client lives in `external/QuotationExternal` and targets a configurable base URL (e.g. `http://localhost:8084/app/quotation`). Ensure the quotation service is running and the URL matches your environment (ideally via configuration).

## Exception handling

- **AssetNotFoundException** — Mapped to `404 NOT FOUND` with a structured body.
- **MethodArgumentNotValidException** (validation errors) — Mapped to `400 BAD REQUEST` with field/default message details.
- **Generic exceptions** — Mapped to `500 INTERNAL SERVER ERROR` with a generic message (avoid exposing internals in production).

Handlers are implemented in `HandlerError` (`@ControllerAdvice`).

## License

This project is for internal or educational use unless otherwise stated.
