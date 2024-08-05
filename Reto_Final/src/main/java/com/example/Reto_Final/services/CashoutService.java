package com.example.Reto_Final.services;

import com.example.Reto_Final.exceptions.CashoutException;
import com.example.Reto_Final.exceptions.InsufficientBalanceException;
import com.example.Reto_Final.exceptions.PaymentException;
import com.example.Reto_Final.model.Cashout;
import com.example.Reto_Final.repository.CashoutRepository;
import com.example.Reto_Final.services.interfaces.ICashoutService;
import com.example.Reto_Final.services.interfaces.IPaymentRestClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CashoutService implements ICashoutService {
    final private CashoutRepository cashoutRepository;
    final private UserService userService;
    final private IPaymentRestClient paymentRestClient;

    public CashoutService(CashoutRepository cashoutRepository,
                          UserService userService,
                          IPaymentRestClient paymentRestClient){
        this.cashoutRepository = cashoutRepository;
        this.userService = userService;
        this.paymentRestClient = paymentRestClient;
    }


    public Mono<Cashout> Crear(Cashout cashout) {
        return userService.ObtenerUsuarioPorId(cashout.getUserId())
                .filter(user -> user.getBalance() >= cashout.getAmount())
                .switchIfEmpty(Mono.error(new InsufficientBalanceException("Balance insuficiente para realizar el cashout")))
                .flatMap(user ->
                        paymentRestClient.RealizaPago(user.getId(), cashout.getAmount())
                                .doOnError(throwable -> System.out.println("Se generó un problema: " + throwable.getMessage()))
                                .flatMap(resultado -> {
                                    switch (resultado) {
                                        case "Rejected":
                                            return Mono.error(new PaymentException(resultado));
                                        default:
                                            return userService.ActualizarBalance(cashout.getUserId(), cashout.getAmount());
                                    }
                                })
                )
                .flatMap(updatedUser -> cashoutRepository.save(cashout)
                        .doOnError(error -> System.err.println("Error al realizar el cashout: " + error.getMessage()))
                        .doOnSuccess(cashoutResult -> System.out.println("Cashout realizado exitosamente: " + cashout.getAmount() + " al usuario: " + cashout.getUserId()))
                        .thenReturn(cashout)
                )
                .flatMap(cashout1 -> ObtenerRetirosPorusuario(cashout1.getUserId())
                        .collectList()
                        .map(cashoutList -> {
                            System.out.println("Lista de cashouts para el usuario " + cashout.getUserId() + ": " + cashoutList);
                            return cashout1;
                        })
                );
    }


    @Override
    public Flux<Cashout> ObtenerRetirosPorusuario(String userId) {
        return cashoutRepository.findAllByUserId(userId)
                .switchIfEmpty(Mono.error(new CashoutException("Cashouts no encontrados por usuario")));
    }
}
