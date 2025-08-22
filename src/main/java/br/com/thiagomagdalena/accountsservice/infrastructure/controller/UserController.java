package br.com.thiagomagdalena.accountsservice.infrastructure.controller;

import br.com.thiagomagdalena.accountsservice.application.interfaces.user.ActivateSubscriptionUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.AuthenticateUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.CreateUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.DeleteUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.GetLoggedUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.GetUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.GetUsersUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.UpdateUserUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.adapters.UserAdapter;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.CreateUserDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.LoginUserDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.RecoveryJwtTokenDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.SubscriptionDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UpdateUserDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Usuários",description = "Api de gerenciamento de usuários")
public class UserController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final GetLoggedUserUseCase getLoggedUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ActivateSubscriptionUseCase activateSubscriptionUseCase;
    private final UserAdapter userAdapter;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Endpoint para autenticar um usuário e retornar um token JWT")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@Valid @RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(authenticateUserUseCase.execute(loginUserDto));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Retorna os dados do usuário logado")
    public ResponseEntity<UserResponse> getLoggedUser(@RequestHeader(value = "X-User-Id") Long xUserId) {
        return ResponseEntity.ok(getLoggedUserUseCase.execute(xUserId));
    }

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Endpoint para criar um novo usuário")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        final var user = userAdapter.toUser(createUserDto);
        return new ResponseEntity<>(createUserUseCase.execute(user), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Listar usuários", description = "Endpoint para listar todos os usuários ou filtrar por tipo de funcionário")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(required = false) String employeeType) {
        return ResponseEntity.ok(getUsersUseCase.execute(employeeType));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Listar usuários por id", description = "Endpoint para listar usuáriospor id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId,
                                                    @RequestHeader(value = "X-User-Id") Long xUserId) {
        return ResponseEntity.ok(getUserUseCase.execute(userId));
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Atualizar usuário", description = "Endpoint para atualizar um usuário existente")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
                                                   @Valid @RequestBody UpdateUserDto updateUserDto,
                                                   @RequestHeader(value = "X-User-Id") Long xUserId) {
        final var user = userAdapter.updateUser(updateUserDto);
        user.setId(userId);
        return ResponseEntity.ok(updateUserUseCase.execute(user));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Deletar usuário", description = "Endpoint para deletar um usuário existente")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/subscription/activate")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Ativar assinatura de um usuário", description = "Endpoint administrativo para ativar uma assinatura.")
    public ResponseEntity<UserResponse> activateSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
        return ResponseEntity.ok(activateSubscriptionUseCase.execute(subscriptionDto));
    }
}