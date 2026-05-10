# Coding Standards

## 1. Language & Compiler

- **Target**: Java 25 (LTS)
- **Source Compatibility**: `--release 25`

## 2. Key Language Features

- **Records**: Immutable data carriers, DTOs, responses
- **Pattern Matching**: `instanceof`, `switch`, `for-each`
- **Text Blocks**: Multiline strings
- **Sealed Classes**: Controlled class hierarchies
- **Var**: Type inference when clarity maintained
- **Unnamed Variables**: `_` for unused values

## 3. Formatting

- **Indentation**: 4 spaces (no tabs)
- **Line width**: 120 characters
- **Blank lines**: One blank line between methods

## 4. Naming Conventions

| Type | Convention | Examples |
|------|------------|------|
| **Classes** | PascalCase | `User`, `Order`, `CreateUser`, `UserResponse` |
| **Methods** | camelCase | `findByUserId`, `processPayment`, `toResponse()` |
| **Variables** | camelCase | `userId`, `totalAmount`, `user` |
| **Constants** | UPPER_SNAKE_CASE | `MAX_RETRY_COUNT` |

## 5. File Organization

```
src/main/java/com/example/
├── domain/           # Model, Command, Service, Port
├── application/      # API, Service
└── infra/           # Persistence (Repository, Adapter)
```

## 6. Annotation Usage

### Lombok
```java
@Value
public record UserRequest(@NotBlank String firstName, @Email String email) {}

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private OrderId id;
    private List<Item> items;
    private Money total;
}
```

### MapStruct
```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "email", target = "email")
    UserResponse toResponse(UserCommand command);
}
```

### Validation
```java
@Validated
public record CreateCommand(@NotBlank String name, @Min(0) @Max(10000) Double amount, @Email String email) {}
```

## 7. Best Practices

### Immutability
```java
@Value
public record OrderSnapshot(OrderId id, LocalDateTime createdAt, OrderStatus status, Money total) {}
```

### Single Responsibility
```java
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final OrderEventPublisher eventPublisher;
    // Focused methods only
}
```

### Exception Handling
```java
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ResourceNotFoundResponse> handleNotFound(ResourceNotFoundException ex) {
    return ResponseEntity.notFound().build();
}
```

### Comments
```java
// Complex logic
// TODO/FIXME: with description and target date
/**
 * Process refund for order.
 * If order has already been shipped, only refund items that haven't been shipped.
 */
public RefundResult processRefund(Order order, Amount amount) { /* ... */ }
```

## 8. Anti-Patterns

### ❌ Magic Numbers
```java
// BAD
if (age > 18 && age < 65) { }

// GOOD
if (age >= MINIMUM_AGE && age < RETIREMENT_AGE) { }
```

### ❌ Deeply Nested Conditions
```java
// BAD
if (order != null && order.getItems() != null && order.getItems().size() > 0) { }

// GOOD
if (isNotNullAndNotEmpty(order.getItems())) { }
```

### ❌ God Objects
```java
// BAD - does everything
@Service
public class OrderService { /* ... */ }

// GOOD - focused responsibility
@Service
public class OrderService { /* ... */ }
```

### ❌ Inheritance Hierarchies
```java
// BAD
abstract class Base { /* ... */ }
class A extends Base { /* ... */ }
class B extends Base { /* ... */ }

// GOOD
class A { /* ... */ }
class B { /* ... */ }
class C implements Base { /* ... */ }