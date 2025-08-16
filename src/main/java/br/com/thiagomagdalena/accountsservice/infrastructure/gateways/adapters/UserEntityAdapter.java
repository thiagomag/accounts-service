package br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters;

import br.com.thiagomagdalena.accountsservice.application.configuration.SecurityConfiguration;
import br.com.thiagomagdalena.accountsservice.domain.entity.User;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UserResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEntityAdapter {

    private final SecurityConfiguration securityConfiguration;
    private final TelephoneEntityAdapter telephoneEntityAdapter;
    private final AddressEntityAdapter addressEntityAdapter;
    private final RoleEntityAdapter roleEntityAdapter;

    public UserEntity toUserEntity(User user) {
        final var userEntity = UserEntity.builder()
                .email(user.getEmail())
                .password(securityConfiguration.passwordEncoder().encode(user.getPassword()))
                .name(user.getFullName())
                .cpf(user.getCpf())
                .build();

        final var telephones = telephoneEntityAdapter.toTelephoneEntity(user.getTelephones());
        final var addresses = addressEntityAdapter.toAddressEntity(user.getAddresses());

        telephones.forEach(telephone -> telephone.setUser(userEntity));
        addresses.forEach(address -> address.setUser(userEntity));

        userEntity.setTelephones(telephones);
        userEntity.setAddresses(addresses);

        return userEntity;
    }

    public UserEntity updateUserEntity(UserEntity userEntity, User user) {
        return UserEntity.builder()
                .id(userEntity.getId())
                .email(user.isEmailChangeAuthorized() && user.getEmail() != null ? user.getEmail() : userEntity.getEmail())
                .password(user.isPasswordChangeAuthorized() && user.getPassword() != null ? securityConfiguration.passwordEncoder().encode(user.getPassword()) : userEntity.getPassword())
                .name(Optional.ofNullable(user.getFullName()).orElse(user.getFullName()))
                .cpf(Optional.ofNullable(user.getCpf()).orElse(user.getCpf()))
                .build();
    }

    public UserResponse toUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .cpf(user.getCpf())
                .telephones(telephoneEntityAdapter.toTelephoneResponse(user.getTelephones()))
                .addresses(addressEntityAdapter.toAddressResponse(user.getAddresses()))
                .role(roleEntityAdapter.toRole(user.getUserRoles()))
                .build();
    }
}
