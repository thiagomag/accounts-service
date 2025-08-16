package br.com.thiagomagdalena.accountsservice.application.usecases.telephone;

import br.com.thiagomagdalena.accountsservice.application.gatways.TelephoneGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.GetTelephoneFromUserUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.TelephoneResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.TelephoneEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTelephoneFromUserUseCaseImpl implements GetTelephoneFromUserUseCase {

    private final TelephoneGateway telephoneGateway;
    private final TelephoneEntityAdapter telephoneEntityAdapter;

    @Override
    public List<TelephoneResponse> execute(Long userId) {
        final var telephones = telephoneGateway.findByUserId(userId);
        return telephoneEntityAdapter.toTelephoneResponse(telephones);
    }
}
