# Architecture Standards

## Pattern: Hexagonal (Ports & Adapters)

**Layers** (Dependency Rule):
| Layer | Responsibility | Dependencies |
|-------|---------------|--------------|
| **Domain** | Business logic, entities, commands, ports | None |
| **Application** | Orchestration, transactions | Domain only |
| **Infrastructure** | Port implementations, external systems | External APIs |

**Structure**:
```
src/main/java/com/example/
├── domain/           # Model, Command, Service, Port
├── application/      # API, Service
└── infra/           # Persistence (Repository, Adapter)
```

## Domain

### Repository Pattern
- **Port (Domain)**: Interface defining operations (e.g., `findById`, `findByEmail`)
- **Adapter (Infra)**: Implementation using persistence technology (e.g., JPA `EntityManager`)

### Entity Example
```java
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
```

### Domain Events
- Create events for business actions (e.g., `UserCreatedEvent`)
- Publish events after persistence in `@Transactional` services
- Handlers process events asynchronously (notifications, audit, analytics)

## API Design

**REST Principles**:
- HTTP verbs: `GET`, `POST`, `PUT`, `DELETE`
- Plural paths: `/users`, `/orders`
- Status codes: `201`, `204`, `404`, `422`

**Controller Example**:
```java
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @GetMapping("/{id}")
    public UserResponse get(@PathVariable String id) { }
    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) { }
}
```

## Cross-Cutting Concerns

| Concern | Solution | Notes |
|---------|----------|-------|
| **Security** | OAuth2 JWT | JWT resource server; public paths excluded |
| **Observability** | AOP Logging | Log entry/exit; clear MDC in `finally` |
| **Caching** | `@Cacheable`/`@CacheEvict` | Cache key by entity ID |
| **Resilience** | Circuit Breaker + Retry | External service failures |
| **Batch** | `@Async` + Kafka | Process events asynchronously |
| **Configuration** | profiles + properties | Dev/Prod profiles; external URLs |
| **Migrations** | Flyway | Schema migrations; baseline on migrate |
| **Versioning** | URI or Header | URI: `/api/v1/`, Header: `Accept` |
| **Feature Flags** | Config Properties | Boolean flags in config |
| **Response Wrapping** | Collection + Error DTOs | Paginated lists; error details |