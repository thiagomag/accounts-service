package br.com.thiagomagdalena.accountsservice.application.interfaces;

public interface UseCase<Input,Output> {
    Output execute(Input input);
}
