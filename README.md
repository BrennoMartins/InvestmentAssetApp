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

All asset-related endpoints are under the `/asset` base path.

| Resource        | Base path                | List | Get by id | Get by query      | Create | Update | Delete |
|----------------|---------------------------|------|-----------|-------------------|--------|--------|--------|
| Assets         | `GET/POST/PUT/DELETE /asset` | ✓    | `/{id}`   | `/query?asset=`   | ✓      | `PUT /{id}` | ✓  |
| Banks          | `/asset/bank`             | ✓    | `/{id}`   | `/query?bank=`    | ✓      | —      | ✓      |
| Asset types    | `/asset/asset-type`      | ✓    | `/{id}`   | `/query?name=`    | ✓      | —      | ✓      |
| Asset sub-types| `/asset/asset-sub-type`  | ✓    | `/{id}`   | `/query?name=`    | ✓      | —      | ✓      |
| Categories     | `/asset/category`        | ✓    | `/{id}`   | `/query?name=`    | ✓      | —      | ✓      |
| Reports        | `/asset/report`          | —    | —         | —                 | —      | —      | —      |

**Reports**

- `GET /asset/report/accounting` — Returns the list of accounting report assets (raw data).

**Asset sub-types (extra)**

- `GET /asset/asset-sub-type/by-asset-type/{assetTypeId}` — List sub-types for a given asset type.

Responses use standard HTTP status codes (200, 201, 204, 400, 404, 500). Not found and validation errors return JSON bodies (e.g. `AssetErrorDetails`).

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
├── controller/                         # REST controllers (Asset, Bank, AssetType, AssetSubType, AssetCategory, AssetReport)
├── excpetion/                         # AssetNotFoundException, HandlerError, AssetErrorDetails
├── external/                           # Quotation API client (QuotationExternal, DTOs)
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
