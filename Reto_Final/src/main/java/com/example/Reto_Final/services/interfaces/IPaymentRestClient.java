package com.example.Reto_Final.services.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IPaymentRestClient {
    @PostExchange("/payment/{userId}/{amount}")
    Mono<String> RealizaPago(@PathVariable("userId") String userId, @PathVariable("amount") Integer amount);
}
