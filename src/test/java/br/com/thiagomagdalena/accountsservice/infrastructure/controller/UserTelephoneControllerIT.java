package br.com.thiagomagdalena.accountsservice.infrastructure.controller;

import br.com.thiagomagdalena.accountsservice.domain.enums.TelephoneTypeEnum;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.TelephoneDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext
public class UserTelephoneControllerIT {

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
    public void shouldCreateUserTelephone() {
        final var telephoneRequest = List.of(new TelephoneDto("+55", "24", "99999-9999", TelephoneTypeEnum.MOBILE));

        RestAssured.given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType("application/json")
                .body(telephoneRequest)
                .when()
                .post("/users/{userId}/telephones", adminUserId)
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldCreateUserTelephoneForBasicUser() {
        final var telephoneRequest = List.of(new TelephoneDto("+55", "24", "99999-9999", TelephoneTypeEnum.MOBILE));

        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .contentType("application/json")
                .body(telephoneRequest)
                .when()
                .post("/users/{userId}/telephones", basicUserId2)
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldNotCreateUserTelephoneForNonAdminUser() {
        final var telephoneRequest = List.of(new TelephoneDto("+55", "24", "99999-9999", TelephoneTypeEnum.MOBILE));

        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .contentType("application/json")
                .body(telephoneRequest)
                .when()
                .post("/users/{userId}/telephones", adminUserId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldNotCreateUserTelephoneForBasicUser() {
        final var telephoneRequest = List.of(new TelephoneDto("+55", "24", "99999-9999", TelephoneTypeEnum.MOBILE));

        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .contentType("application/json")
                .body(telephoneRequest)
                .when()
                .post("/users/{userId}/telephones", adminUserId)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldGetUserTelephones() {
        RestAssured.given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .get("/users/{userId}/telephones", adminUserId)
                .then()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.greaterThan(0));
    }

    @Test
    public void shouldGetUserTelephonesForBasicUser() {
        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .when()
                .get("/users/{userId}/telephones", basicUserId2)
                .then()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.greaterThan(0));
    }

    @Test
    public void shouldNotGetUserTelephonesForNonAdminUser() {
        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .when()
                .get("/users/{userId}/telephones", adminUserId)
                .then()
                .statusCode(403);
    }


    @Test
    public void shouldUpdateUserTelephone() {
        final var telephoneRequest = new TelephoneDto("+55", "21", "88888-8888", TelephoneTypeEnum.MOBILE);

        RestAssured.given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .contentType("application/json")
                .body(telephoneRequest)
                .when()
                .put("/users/{userId}/telephones/{telephoneId}", adminUserId, 5L)
                .then()
                .statusCode(200)
                .body("area_code", equalTo("21"))
                .body("number", equalTo("88888-8888"));
    }

    @Test
    public void shouldUpdateUserTelephoneForBasicUser() {
        final var telephoneRequest = new TelephoneDto("+55", "21", "88888-8888", TelephoneTypeEnum.MOBILE);

        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .contentType("application/json")
                .body(telephoneRequest)
                .when()
                .put("/users/{userId}/telephones/{telephoneId}", basicUserId2, 3L)
                .then()
                .statusCode(200)
                .body("area_code", equalTo("21"))
                .body("number", equalTo("88888-8888"));
    }

    @Test
    public void shouldNotUpdateUserTelephoneForNonAdminUser() {
        final var telephoneRequest = new TelephoneDto("+55", "21", "88888-8888", TelephoneTypeEnum.MOBILE);

        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .contentType("application/json")
                .body(telephoneRequest)
                .when()
                .put("/users/{userId}/telephones/{telephoneId}", adminUserId, 1L)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldDeleteUserTelephone() {
        RestAssured.given()
                .header("X-User-Roles", "ROLE_ADMIN")
                .header("X-User-Id", adminUserId)
                .when()
                .delete("/users/{userId}/telephones/{telephoneId}", adminUserId, 1L)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldDeleteUserTelephoneForBasicUser() {
        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .delete("/users/{userId}/telephones/{telephoneId}", basicUserId, 2L)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldNotDeleteUserTelephoneForNonAdminUser() {
        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId2)
                .when()
                .delete("/users/{userId}/telephones/{telephoneId}", adminUserId, 1L)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldNotDeleteUserTelephoneForBasicUser() {
        RestAssured.given()
                .header("X-User-Roles", "ROLE_BASIC")
                .header("X-User-Id", basicUserId)
                .when()
                .delete("/users/{userId}/telephones/{telephoneId}", adminUserId, 1L)
                .then()
                .statusCode(403);
    }

}
