package br.com.thiagomagdalena.accountsservice.infrastructure.controller;

import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddressDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext
public class UserAddressControllerIT {

    @LocalServerPort
    private int port;

    private final Long adminUserId = 1L;
    private final Long basicUserId = 2L;
    private final Long basicUserId2 = 3L;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void shouldCreateUserAddressSuccessfully() {
        final var newAddress = List.of(new AddressDto("Rua A", "123", "", "Bairro B", "Cidade C", "Estado D", "Brasil", "12345-678"));

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType("application/json")
                .body(newAddress)
                .when()
                .post("/users/{userId}/addresses", adminUserId)
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldCreateUserAddressWithBasicUserRole() {
        final var newAddress = List.of(new AddressDto("Rua A", "123", "", "Bairro B", "Cidade C", "Estado D", "Brasil", "12345-678"));

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .contentType("application/json")
                .body(newAddress)
                .when()
                .post("/users/{userId}/addresses", basicUserId2)
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldReturnForbiddenWhenCreatingAddressWithBasicRole() {
        final var newAddress = List.of(new AddressDto("Rua A", "123", "", "Bairro B", "Cidade C", "Estado D", "Brasil", "12345-678"));

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .contentType("application/json")
                .body(newAddress)
                .when()
                .post("/users/{userId}/addresses", adminUserId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldReturnNotFoundWhenCreateUserAddressesForNonExistentUser() {
        final var newAddress = List.of(new AddressDto("Rua A", "123", "", "Bairro B", "Cidade C", "Estado D", "Brasil", "12345-678"));

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType("application/json")
                .body(newAddress)
                .when()
                .post("/users/{userId}/addresses", 999)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldReturnEmptyWhenGetUserAddresses() {
        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .get("/users/{userId}/addresses", 999)
                .then()
                .statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test
    public void shouldGetUserAddressesSuccessfully() {
        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .get("/users/{userId}/addresses", adminUserId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void shouldGetUserAddressesWithBasicUserRole() {
        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .when()
                .get("/users/{userId}/addresses", basicUserId2)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void shouldGetUserAddressesWithBasicRoleReturnError() {
        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .get("/users/{userId}/addresses", adminUserId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldUpdateUserAddressSuccessfully() {
        final var updatedAddress = new AddressDto("Rua Atualizada", "456", "", "Bairro Atualizado", "Cidade Atualizada", "Estado Atualizado", "Brasil", "87654-321");
        final Long addressId = 1L;

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType("application/json")
                .body(updatedAddress)
                .when()
                .put("/users/{userId}/addresses/{addressId}", adminUserId, addressId)
                .then()
                .statusCode(200)
                .body("street", equalTo("Rua Atualizada"))
                .body("number", equalTo("456"))
                .body("neighborhood", equalTo("Bairro Atualizado"))
                .body("city", equalTo("Cidade Atualizada"));
    }

    @Test
    public void shouldUpdateUserAddressWithBasicUserRole() {
        final var updatedAddress = new AddressDto("Rua Atualizada", "456", "", "Bairro Atualizado", "Cidade Atualizada", "Estado Atualizado", "Brasil", "87654-321");
        final Long addressId = 2L;

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .contentType("application/json")
                .body(updatedAddress)
                .when()
                .put("/users/{userId}/addresses/{addressId}", basicUserId, addressId)
                .then()
                .statusCode(200)
                .body("street", equalTo("Rua Atualizada"))
                .body("number", equalTo("456"))
                .body("neighborhood", equalTo("Bairro Atualizado"))
                .body("city", equalTo("Cidade Atualizada"));
    }

    @Test
    public void shouldReturnForbiddenWhenUpdatingAddressWithBasicRole() {
        final var updatedAddress = new AddressDto("Rua Atualizada", "456", "", "Bairro Atualizado", "Cidade Atualizada", "Estado Atualizado", "Brasil", "87654-321");
        final Long addressId = 1L;

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .contentType("application/json")
                .body(updatedAddress)
                .when()
                .put("/users/{userId}/addresses/{addressId}", adminUserId, addressId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldReturnNotFoundWhenUpdatingNonExistentAddress() {
        final var updatedAddress = new AddressDto("Rua Atualizada", "456", "", "Bairro Atualizado", "Cidade Atualizada", "Estado Atualizado", "Brasil", "87654-321");
        final Long nonExistentAddressId = 999L;

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType("application/json")
                .body(updatedAddress)
                .when()
                .put("/users/{userId}/addresses/{addressId}", adminUserId, nonExistentAddressId)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldDeleteUserAddressSuccessfully() {
        final Long addressId = 1L;

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .delete("/users/{userId}/addresses/{addressId}", adminUserId, addressId)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldDeleteUserAddressWithBasicUserRole() {
        final Long addressId = 2L;

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .delete("/users/{userId}/addresses/{addressId}", basicUserId, addressId)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldReturnForbiddenWhenDeletingAddressWithBasicRole() {
        final Long addressId = 1L;

        given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .delete("/users/{userId}/addresses/{addressId}", adminUserId, addressId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldReturnNotFoundWhenDeletingNonExistentAddress() {
        final Long nonExistentAddressId = 999L;

        given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .delete("/users/{userId}/addresses/{addressId}", adminUserId, nonExistentAddressId)
                .then()
                .statusCode(404);
    }
}
