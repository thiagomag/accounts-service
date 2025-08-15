package br.com.thiagomagdalena.accountsservice.infraestructure.controller;

import br.com.thiagomagdalena.accountsservice.application.interfaces.address.AddAddressToUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.address.GetAddressFromUserUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.address.UpdateAddressUseCase;
import br.com.thiagomagdalena.accountsservice.application.interfaces.address.DeleteAddressUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.adapters.AddressAdapter;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address.*;
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
@RequestMapping("/users/{userId}/addresses")
@Tag(name = "Addresses",description = "Api de gerenciamento de endereços")
public class UserAddressController {

    private final AddressAdapter addressAdapter;
    private final AddAddressToUserUseCase addAddressToUserUseCase;
    private final GetAddressFromUserUseCase getAddressFromUserUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Adicionar endereço para o usuário", description = "Endpoint para adicionar um endereço a um usuário existente")
    public ResponseEntity<List<AddressResponse>> addAddresses(@RequestBody List<AddressDto> addressDtoList,
                                                              @PathVariable Long userId,
                                                              @RequestHeader(value = "X-User-Id") Long xUserId) {
        final var addresses = addressAdapter.toAddress(addressDtoList);
        final var addAddressRequest = AddAddressRequest.builder()
                .addresses(addresses)
                .userId(userId)
                .build();
        return new ResponseEntity<>(addAddressToUserUseCase.execute(addAddressRequest), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Listar endereços do usuário", description = "Endpoint para listar todos os endereços de um usuário")
    public ResponseEntity<List<AddressResponse>> getUserAddresses(@PathVariable Long userId,
                                                                  @RequestHeader(value = "X-User-Id") Long xUserId) {
        return ResponseEntity.ok(getAddressFromUserUseCase.execute(userId));
    }

    @PutMapping("/{addressId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Atualizar endereço do usuário", description = "Endpoint para atualizar o endereço de um usuário pelo id do endereço")
    public ResponseEntity<AddressResponse> updateUserAddresses(@PathVariable Long userId,
                                                               @PathVariable Long addressId,
                                                               @RequestBody AddressDto addressDto,
                                                               @RequestHeader(value = "X-User-Id") Long xUserId) {
        final var address = addressAdapter.toAddress(addressDto);
        final var addAddressRequest = UpdateAddressRequest.builder()
                .address(address)
                .userId(userId)
                .addressId(addressId)
                .build();
        return ResponseEntity.ok(updateAddressUseCase.execute(addAddressRequest));
    }

    @DeleteMapping("/{addressId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or (hasRole('ROLE_BASIC') and #userId == #xUserId)")
    @Operation(summary = "Deletar endereço do usuário", description = "Endpoint para deletar o endereço de um usuário pelo id do endereço")
    public ResponseEntity<Void> deleteUserAddresses(@PathVariable Long userId,
                                                    @PathVariable Long addressId,
                                                    @RequestHeader(value = "X-User-Id") Long xUserId) {
        final var deleteAddressRequest = DeleteAddressRequest.builder()
                .userId(userId)
                .addressId(addressId)
                .build();
        deleteAddressUseCase.execute(deleteAddressRequest);
        return ResponseEntity.noContent().build();
    }
}