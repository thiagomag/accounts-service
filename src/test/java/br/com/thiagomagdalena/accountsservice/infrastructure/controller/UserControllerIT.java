package br.com.thiagomagdalena.accountsservice.infrastructure.controller;

import br.com.thiagomagdalena.accountsservice.domain.enums.TelephoneTypeEnum;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddressDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.TelephoneDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.CreateUserDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.LoginUserDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.SubscriptionDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UpdateUserDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext
public class UserControllerIT {

    @LocalServerPort
    private int port;

    private final Long adminUserId = 1L;
    private final Long basicUserId = 2L;
    private final Long basicUserId2 = 3L;
    private final Long basicUserId3 = 4L;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void shouldCreateUserSuccessfully() {
        final var userDto = new CreateUserDto(
                "teste@teste.com",
                "123456",
                "Teste",
                "11111111111",
                List.of(new TelephoneDto("+55", "24", "99999-9999", TelephoneTypeEnum.MOBILE)),
                List.of(new AddressDto("Rua A", "123", "", "Bairro B", "Cidade C", "Estado D", "Brasil", "12345-678")));

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", equalTo(5))
                .body("email", equalTo("teste@teste.com"))
                .body("name", equalTo("Teste"))
                .body("cpf", equalTo("11111111111"))
                .body("telephones[0].country_code", equalTo("+55"))
                .body("telephones[0].area_code", equalTo("24"))
                .body("telephones[0].number", equalTo("99999-9999"))
                .body("telephones[0].type", equalTo(TelephoneTypeEnum.MOBILE.name()))
                .body("addresses[0].street", equalTo("Rua A"))
                .body("addresses[0].number", equalTo("123"))
                .body("addresses[0].complement", equalTo(""))
                .body("addresses[0].neighborhood", equalTo("Bairro B"))
                .body("addresses[0].city", equalTo("Cidade C"))
                .body("addresses[0].state", equalTo("Estado D"))
                .body("addresses[0].country", equalTo("Brasil"))
                .body("addresses[0].postal_code", equalTo("12345-678"));
    }

    @Test
    public void shouldReturnBadRequestWhenCreatingUserWithInvalidData() {
        final var userDto = new CreateUserDto(
                "invalid-email",
                "123",
                "",
                "invalid-cpf",
                List.of(),
                List.of());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .post("/users")
                .then()
                .statusCode(400)
                .body("$", hasKey("email"))
                .body("email", equalTo("O e-mail deve ser válido."))
                .body("$", hasKey("password"))
                .body("password", equalTo("A senha deve ter no mínimo 6 caracteres."))
                .body("$", hasKey("fullName"))
                .body("fullName", equalTo("O nome completo é obrigatório."))
                .body("$", hasKey("telephones"))
                .body("telephones", equalTo("O telefone é obrigatório."))
                .body("$", hasKey("addresses"))
                .body("addresses", equalTo("O endereço é obrigatório."));
    }

    @Test
    public void shouldReturnNotFoundWhenUserDoesNotExist() {
        given()
                // Simula um admin logado, injetando os headers que o Gateway enviaria
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .get("/users/999")
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldReturnUserWhenSearchedByIdWithAdminRole() {
        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .get("/users/{userId}", basicUserId3)
                .then()
                .statusCode(200)
                .body("id", equalTo(basicUserId3.intValue()))
                .body("email", equalTo("ana.costa@email.com"));
    }

    @Test
    public void shouldReturnNotFoundWhenSearchedByIdADeletedUser() {
        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .get("/users/{userId}", basicUserId2)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldReturnForbiddenWhenBasicUserTriesToAccessAnotherUser() {
        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .get("/users/{userId}", adminUserId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldAllowBasicUserToAccessTheirOwnData() {
        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .get("/users/{userId}", basicUserId)
                .then()
                .statusCode(200)
                .body("id", equalTo(basicUserId.intValue()));
    }

    @Test
    public void shouldLoginUserSuccessfully() {
        final var loginDto = new LoginUserDto("joao.silva@email.com", "senha123");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginDto)
                .when()
                .post("/users/login")
                .then()
                .statusCode(200)
                .body("$", hasKey("token"))
                .body("token", not(emptyOrNullString()));
    }

    @Test
    public void shouldReturnUnauthorizedWhenLoginWithInvalidCredentials() {
        final var loginDto = new LoginUserDto("joao.silva@email.com", "wrongpassword");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginDto)
                .when()
                .post("/users/login")
                .then()
                .statusCode(401)
                .body("error", equalTo("Security violation: Usuário inexistente ou senha inválida"));
    }

    @Test
    public void shouldReturnBadRequestWhenLoginWithInvalidEmailFormat() {
        final var loginDto = new LoginUserDto("invalid-email", "senha123");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginDto)
                .when()
                .post("/users/login")
                .then()
                .statusCode(400)
                .body("email", equalTo("O e-mail deve ser válido."));
    }

    @Test
    public void shouldReturnBadRequestWhenLoginWithEmptyPassword() {
        final var loginDto = new LoginUserDto("joao.silva@email.com", "");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginDto)
                .when()
                .post("/users/login")
                .then()
                .statusCode(400)
                .body("password", equalTo("A senha é obrigatória."));
    }

    @Test
    public void shouldReturnAllUsersWhenAdmin() {
        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    public void shouldReturnForbiddenWhenBasicUserTriesToAccessAllUsers() {
        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .get("/users")
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldReturnOkWhenUpdatingUserWithAdminRole() {
        final var userDto = UpdateUserDto.builder().fullName("Novo Nome").email("novoemail@email.com").emailChangeAuthorized(true).build();

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .patch("/users/{userId}", basicUserId)
                .then()
                .statusCode(200)
                .body("id", equalTo(basicUserId.intValue()))
                .body("email", equalTo("novoemail@email.com"))
                .body("name", equalTo("Novo Nome"));
    }

    @Test
    public void shouldReturnForbiddenWhenBasicUserTriesToUpdateAnotherUser() {
        final var userDto = UpdateUserDto.builder().fullName("Novo Nome").email("novoemail@email.com").emailChangeAuthorized(true).build();

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .patch("/users/{userId}", adminUserId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldUpdateOwnUserSuccessfully() {
        final var userDto = UpdateUserDto.builder().fullName("Novo Nome").email("novoemail@email.com").emailChangeAuthorized(true).build();

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .patch("/users/{userId}", basicUserId)
                .then()
                .statusCode(200)
                .body("id", equalTo(basicUserId.intValue()))
                .body("email", equalTo("novoemail@email.com"))
                .body("name", equalTo("Novo Nome"));
    }

    @Test
    public void shouldReturnBadRequestWhenUpdatingUserWithInvalidData() {
        final var userDto = UpdateUserDto.builder().email("invalid-email").emailChangeAuthorized(true).build();

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .patch("/users/{userId}", basicUserId)
                .then()
                .statusCode(400)
                .body("$", hasKey("email"))
                .body("email", equalTo("O e-mail deve ser válido."));
    }

    @Test
    public void shouldReturnNotFoundWhenUpdatingNonExistentUser() {
        final var userDto = UpdateUserDto.builder().fullName("Novo Nome").email("novoemail@email.com").emailChangeAuthorized(true).build();

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userDto)
                .when()
                .patch("/users/{userId}", 999)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldDeleteUserSuccessfully() {
        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .delete("/users/{userId}", basicUserId2)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldReturnForbiddenWhenBasicUserTriesToDeleteAnotherUser() {
        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .delete("/users/{userId}", adminUserId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldReturnNotFoundWhenDeletingNonExistentUser() {
        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .delete("/users/{userId}", 999)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldActivateSubscriptionForUser() {
        final var subscriptionDto = new SubscriptionDto(2L, 365);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .body(subscriptionDto)
                .when()
                .post("/users/subscription/activate")
                .then()
                .statusCode(200)
                .body("subscription_status", equalTo("ACTIVE"))
                .body("subscription_end_date", notNullValue());
    }

    @Test
    public void shouldReturnForbiddenWhenActivatingSubscriptionAsBasicUser() {
        final var subscriptionDto = new SubscriptionDto(2L, 365);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .body(subscriptionDto)
                .when()
                .post("/users/subscription/activate")
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldReturnBadRequestWhenActivatingSubscriptionWithInvalidData() {
        final var subscriptionDto = new SubscriptionDto(null, 35);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .body(subscriptionDto)
                .when()
                .post("/users/subscription/activate")
                .then()
                .statusCode(400)
                .body("$", hasKey("userId"))
                .body("userId", equalTo("O ID do usuário não pode ser nulo."));
    }

    @Test
    public void shouldReturnBadRequestWhenActivatingSubscriptionWithInvalidData2() {
        final var subscriptionDto = new SubscriptionDto(1L, -1);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .body(subscriptionDto)
                .when()
                .post("/users/subscription/activate")
                .then()
                .statusCode(400)
                .body("$", hasKey("durationInDays"))
                .body("durationInDays", equalTo("A duração da assinatura não pode ser um valor negativo."));
    }

    @Test
    public void shouldReturnBadRequestWhenActivatingSubscriptionForNonExistentUser() {
        final var subscriptionDto = new SubscriptionDto(999L, 365);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .body(subscriptionDto)
                .when()
                .post("/users/subscription/activate")
                .then()
                .statusCode(404)
                .body("error", equalTo("Entity not found: User not found"));
    }

    @Test
    public void shouldReturnBadRequestWhenActivatingSubscriptionForUserWithActiveSubscription() {
        final var subscriptionDto = new SubscriptionDto(2L, 365);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .body(subscriptionDto)
                .when()
                .post("/users/subscription/activate")
                .then()
                .statusCode(400)
                .body("error", equalTo("Invalid argument: User already has an active subscription"));
    }
}
