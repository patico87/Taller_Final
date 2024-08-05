package com.example.Reto_Final.services.interfaces;

import com.example.Reto_Final.model.Cashout;
import com.example.Reto_Final.model.User;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICashoutService {

    Mono<Cashout> Crear(Cashout cashout);
    Flux<Cashout> ObtenerRetirosPorusuario(String userId);


}
