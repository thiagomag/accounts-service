package br.com.thiagomagdalena.accountsservice.domain.entity;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String cpf;
    private List<Telephone> telephones;
    private List<Address> addresses;
    private Boolean passwordChangeAuthorized;
    private Boolean emailChangeAuthorized;

    public Boolean isPasswordChangeAuthorized() {
        return passwordChangeAuthorized != null && passwordChangeAuthorized;
    }

    public Boolean isEmailChangeAuthorized() {
        return emailChangeAuthorized != null && emailChangeAuthorized;
    }
}


