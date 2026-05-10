# Test Requirements

## 2. Unit Tests

### Requirements
- **Coverage**: ≥80% for domain/application
- **Execution**: <50ms per test
- **Isolation**: No external dependencies
- **Naming**: `{ClassName}{MethodName}Test` or `{ClassName}{Behavior}Test`

### Example
```java
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private EventPublisher eventPublisher;
    @InjectMocks private UserService userService;

    @Test
    void create_user_shouldPersistAndPublishCreatedEvent() {
        CreateUserCommand command = new CreateUserCommand("John", "john@example.com");
        when(userRepository.save(any())).thenAnswer(a -> 
            new User(command.name(), command.email(), UserStatus.ACTIVE, Instant.now())
        );

        UserResponse result = userService.create(command);

        assertThat(result).isNotNull();
        assertThat(result.id()).isNotEmpty();
        verify(userRepository).save(any(User.class));
        verify(eventPublisher).publish(any(CreateUserEvent.class));
    }

    @Test
    void create_user_shouldThrowWhenEmailInvalid() {
        assertThrows(BadRequestException.class,
            () -> userService.create(new CreateUserCommand("John", "not-an-email"))
        );
    }
}
```

### Test Categories
- `@SpringBootTest` - Integration tests with test DB
- `@WebMvcTest` - Controller layer unit tests
- Plain class - Pure unit tests for domain models

## 3. Integration Tests

```java
@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserIntegrationTests {
    @Autowired private TestDatabaseAwareDatabaseSeeder seeder;

    @BeforeEach
    void setup() { seeder.seed(); }

    @Test
    void createUserAndReadBack_ShouldSucceed() { ... }
}
```

**Database Requirements:**
- Use **H2 in-memory** for most integration tests
- Configure **DDL auto** for clean state between tests
- Use **Testcontainers** for complex scenarios (PostgreSQL, Kafka)
- Use **`@Transactional`** test methods for faster execution

## 4. Acceptance Tests

### Criteria
- End-to-end scenarios with test data
- API contract validation
- Happy path + critical failure cases
- Performance thresholds met

```java
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"schema.sql", "data.sql"})
class UserFlowAcceptanceTests {
    @Autowired private MockMvc mockMvc;

    @Test
    void createUserAndFetch_ShouldReturnValidUser() throws Exception {
        CreateUserCommand command = CreateUserCommand.builder()
            .name("Test User")
            .email("test@example.com")
            .build();

        var createRequest = new UserRequest(command.name(), command.email());
        UserResponse created = mockMvc.perform(
            post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Test User"))
        .andReturn().getResponse.getContentAsString();

        UserResponse read = mockMvc.perform(
            get("/api/users/{id}", created.id())
        )
        .andExpect(status().isOk())
        .andReturn().getResponse.getContentAsString();
    }
}
```

## 5. Test Data Fixtures

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTests {
    private User createStandardUser() {
        return User.builder()
            .id(new UserId("user-1"))
            .name("John Doe")
            .email("john@example.com")
            .status(UserStatus.ACTIVE)
            .build();
    }

    private Order createStandardOrder() {
        return Order.builder()
            .id(new OrderId("order-1"))
            .userId(new UserId("user-1"))
            .items(List.of(
                Item.builder().productId("prod-1").quantity(2)
                    .unitPrice(new Money(100, Currency.EUR)).build()
            ))
            .status(OrderStatus.PENDING)
            .build();
    }
}
```

## 8. Best Practices

- ✅ **Arrange-Act-Assert**: Follow AAA pattern clearly
- ✅ **Mock Only What's Needed**: Don't mock domain services in domain tests
- ✅ **Test Behavior Not Implementation**: Focus on what, not how
- ✅ **One Assertion Per Test**: Or clearly grouped assertions
- ✅ **Readable Names**: Test names should describe behavior
- ✅ **Minimal Setup**: Use `@BeforeEach` for test setup
- ✅ **Clear Assertions**: Use descriptive assertion messages

## 9. Anti-Patterns

### ❌ Tests That Test Implementation Details
```java
// BAD
@Test
void test_returnsUserWithCorrectId() {
    User user = userService.findById(userId);
    assertEquals(userId.getValue(), user.getId().getValue());
}

// GOOD
@Test
void findById_shouldReturnActiveUser() {
    // Arrange
    User user = givenUser();
    when(userRepository.findById(any())).thenReturn(Optional.of(user));

    // Act & Assert
    assertThat(userService.findById(userId)).isPresent();
}
```

### ❌ Too Many Assertions in One Test
```java
// BAD - 20+ assertions in one test
// GOOD - Split into focused tests with clear names
```

### ❌ Overly Complex Test Data
```java
// BAD - 100+ lines of setup
private User createComplexUserWithAllRelations() { ... }

// GOOD - Reuse fixtures
private User createStandardUser() { return User.builder()...; }