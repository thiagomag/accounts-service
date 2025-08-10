package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.gatways.AddressGateway;
import br.com.thiagomagdalena.accountsservice.application.gatways.TelephoneGateway;
import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.DeleteUserUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserGateway userGateway;
    private final TelephoneGateway telephoneGateway;
    private final AddressGateway addressGateway;

    @Override
    public Void execute(Long userId) {
        final var userEntity = userGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userGateway.delete(userEntity);
        userEntity.getTelephones().forEach(telephoneGateway::delete);
        userEntity.getAddresses().forEach(addressGateway::delete);
        return null;
    }
}
