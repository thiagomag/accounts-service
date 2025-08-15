package br.com.thiagomagdalena.accountsservice.infraestructure.controller;

import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.AddTelephoneToUserUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.adapters.TelephoneAdaper;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone.AddTelephoneRequest;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone.TelephoneDto;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone.TelephoneResponse;
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
}
