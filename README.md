# My Spring Boot Project


Spring Boot 3.4.3 with JWT authentication Database Project.
```bash
https://start.spring.io/
```

# Project Overview /概要
このプロジェクトは、Spring Boot をベースにした RESTful API サーバーの構築を通じて、認証、データベース、DTO変換、ドキュメント生成、そしてアノテーションベース設計を学ぶ教育的テンプレートです。

This project is an educational Spring Boot template designed to teach you how to build a RESTful API server with authentication, database persistence, DTO mapping, API documentation, and annotation-driven design.

---
#REST API サーバー構築 / REST API Server Setup
@RestController, @GetMapping, @PostMapping を用いて HTTP エンドポイントを提供。
Spring Boot の自動構成機能（組み込みTomcat、JacksonによるJSON変換）で、最小限の設定でWebアプリ起動。

Uses @RestController, @GetMapping, and @PostMapping to define HTTP endpoints.
Bootstraps a web server with minimal configuration using Spring Boot's auto-configuration (embedded Tomcat, JSON conversion via Jackson).

---
# JWT認証によるセキュリティ / JWT-Based Security
spring-boot-starter-security + io.jsonwebtoken を使って、トークンベースの認証システムを構築。

security/ 以下に、トークンサービス、認証フィルター、ユーザー詳細サービスなどのセキュリティ層を実装。

Implements token-based authentication using spring-boot-starter-security and io.jsonwebtoken.
The security/ package contains JWT token services, authentication filters, and user detail services.

---
# データ永続化（JPA + 複数DB対応）/ Data Persistence with JPA + Multiple DB Support
@Entity + Spring Data JPA によって、PostgreSQL/H2 データベースへのデータ永続化を実現。

開発用には H2、本番・テスト用には PostgreSQL に切り替え可能。
data.sql による初期データ投入、TestContainers を使った DB統合テストも可能。

Uses @Entity and Spring Data JPA to persist data in PostgreSQL or H2.
Supports DB initialization with data.sql, and database integration testing with TestContainers.
---
# DTO変換とOpenAPI生成 / DTO Mapping & OpenAPI Documentation
MapStruct を用いて、Entity ⇔ DTO の変換を自動化。

springdoc-openapi によって、Swagger UI による API ドキュメントを自動生成。

Automates DTO ↔ Entity mapping using MapStruct.
Generates Swagger-based API documentation using springdoc-openapi.


---
# アノテーション活用の宣言的構成 / Declarative Design with Rich Annotations
@Service, @Repository, @Entity, @Mapper, @Bean, @Configuration, @Valid などを活用し、宣言的で責務が分離されたコード設計を実現。

Emphasizes declarative design using annotations like @Service, @Repository, @Entity, @Mapper, @Bean, @Configuration, @Valid.
The project is structured to support annotation-centric learning and clean layered architecture.


---


##  Tech Stack/技術スタック

- Java 17+
- Spring Boot
- Spring Web (REST API)
- Lombok（Free）
- Maven
- pom.xml
- application.yml


---

##  Folder Structure/フォルダ構成


- Java 17+
- Spring Boot 3.4.3
- Spring Security（JWT Authentication）
- Spring Data JPA（PostgreSQL, H2）
- MapStruct（DTO calibre）
- SpringDoc OpenAPI 3（Rest API）
- Lombok
- TestContainers（PostgreSQL用）
- H2 database

```bash
src/
├── main/
│ ├── java/
│ │ └── org/mik/first/
│ │ ├── controller/ # REST API controller
│ │ ├── service/ # Interface
│ │ ├── model/ # Entity・DTO
│ │ └── security/ # JWT Authentication
│ └── resources/
│ ├── application.properties
│ └── data.sql（H2 Database）
└── test/
└── java/... # JUnit + TestContainers
```

---

##  Activation / 起動方法

```bash
# Clone
git clone https://github.com/129N/SpringBootInitialize-.git
cd SpringBootInitialize-

# Build
./mvnw clean install

# Run
./mvnw spring-boot:run
```

#.Port Access /ポートにアクセス
```bash
 http://localhost:8080 
```

---

## Maven dependecies and properties / Maven プロパティ（pom.xmlより抜粋）
```bash
| properties      | Value       |
| --------------- | ----------- |
| Java Version    | 17          |
| Server Port     | 8080        |
| MapStruct       | 1.5.5.Final |
| OpenAPI Version | 2.7.0       |
| JWT Version     | 0.12.6      |
| JWT Secret      | `aaa`       |
| JWT Expiration  | 3600 sec    |


```


---

---

##  Learning Purpose / 学習目的
Annotation learning 

###  Annotations

| Annotation |　Description |
|----------------|------|
| `@RestController` | REST API |
| `@Service` | Entities for role |
| `@Repository` | Define data access |
| `@Entity` / `@Table` | JPAEntity & mapping |
| `@Id`, `@GeneratedValue` | identification |
| `@Autowired` / `@RequiredArgsConstructor` | DDirect Injection |
| `@PostMapping`, `@GetMapping` など | HTTP routing |
| `@PreAuthorize`, `@Secured` | security |
| `@Valid`, `@NotNull`, `@Size` | Validation |
| `@Mapper`（MapStruct） | DTO/Entity auto calibration |
| `@Configuration`, `@Component`, `@Bean` | DI setting |
| `@Slf4j`, `@Data`, `@Builder`（Lombok） | log、Getter/Setter、builder |



---






