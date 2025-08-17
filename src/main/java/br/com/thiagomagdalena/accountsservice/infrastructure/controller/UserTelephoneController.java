package br.com.thiagomagdalena.accountsservice.infrastructure.controller;

import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.AddTelephoneToUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.DeleteTelephoneUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.GetTelephoneFromUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.UpdateTelephoneUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.adapters.TelephoneAdaper;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/telephones")
@Tag(name = "Telephones",description = "Api de gerenciamento de telefones")
public class UserTelephoneController {

    private final TelephoneAdaper telephoneAdapter;
    private final AddTelephoneToUserUseCase addAddressToUserUseCase;
    private final GetTelephoneFromUserUseCase getTelephoneFromUserUseCase;
    private final UpdateTelephoneUseCase updateTelephoneUseCase;
    private final DeleteTelephoneUseCase deleteTelephoneUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Adicionar endereço para o usuário", description = "Endpoint para adicionar um endereço a um usuário existente")
    public ResponseEntity<List<TelephoneResponse>> addTelephones(@RequestBody List<TelephoneDto> telephoneDtos,
                                                                 @PathVariable Long userId,
                                                                 @RequestHeader(value = "X-User-Id") Long xUserId) {
        final var telephones = telephoneAdapter.toTelephone(telephoneDtos);
        final var addAddressRequest = AddTelephoneRequest.builder()
                .telephones(telephones)
                .userId(userId)
                .build();
        return new ResponseEntity<>(addAddressToUserUseCase.execute(addAddressRequest), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Listar telefones do usuário", description = "Endpoint para listar todos os telefones de um usuário")
    public ResponseEntity<List<TelephoneResponse>> getUserTelephones(@PathVariable Long userId,
                                                                     @RequestHeader(value = "X-User-Id") Long xUserId) {
        return ResponseEntity.ok(getTelephoneFromUserUseCase.execute(userId));
    }

    @PutMapping("/{telephoneId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Atualizar telefone do usuário", description = "Endpoint para atualizar o telefone de um usuário pelo id do telefone")
    public ResponseEntity<TelephoneResponse> updateUserTelephones(@PathVariable Long userId,
                                                                  @PathVariable Long telephoneId,
                                                                  @RequestBody TelephoneDto telephoneDto,
                                                                  @RequestHeader(value = "X-User-Id") Long xUserId) {
        final var telephone = telephoneAdapter.toTelephone(telephoneDto);
        final var updateAddressRequest = UpdateTelephoneRequest.builder()
                .userId(userId)
                .telephoneId(telephoneId)
                .telephone(telephone)
                .build();
        return ResponseEntity.ok(updateTelephoneUseCase.execute(updateAddressRequest));
    }

    @DeleteMapping("/{telephoneId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Deletar telefone do usuário", description = "Endpoint para deletar o telefone de um usuário pelo id do telefone")
    public ResponseEntity<Void> deleteUserTelephones(@PathVariable Long userId,
                                                     @PathVariable Long telephoneId,
                                                     @RequestHeader(value = "X-User-Id") Long xUserId) {
        final var deleteAddressRequest = DeleteTelephoneRequest.builder()
                .userId(userId)
                .telephoneId(telephoneId)
                .build();
        deleteTelephoneUseCase.execute(deleteAddressRequest);
        return ResponseEntity.noContent().build();
    }
}
