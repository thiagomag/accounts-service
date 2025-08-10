package br.com.thiagomagdalena.accountsservice.infraestructure.controller.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.User;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.CreateUserDto;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    private final TelephoneAdaper telephoneAdapter;
    private final AddressAdapter addressAdapter;

    public User toUser(CreateUserDto createUserDto) {
       return User.builder()
               .fullName(createUserDto.fullName())
               .email(createUserDto.email())
               .password(createUserDto.password())
               .cpf(createUserDto.cpf())
               .telephones(telephoneAdapter.toTelephone(createUserDto.telephones()))
               .addresses(addressAdapter.toAddress(createUserDto.addresses()))
               .build();
    }

    public User updateUser(UpdateUserDto updateUserDto) {
        return User.builder()
                .fullName(updateUserDto.getFullName())
                .email(updateUserDto.getEmail())
                .password(updateUserDto.getPassword())
                .emailChangeAuthorized(updateUserDto.getEmailChangeAuthorized())
                .passwordChangeAuthorized(updateUserDto.getPasswordChangeAuthorized())
                .build();
    }
}
