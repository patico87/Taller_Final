package com.example.Reto_Final.services.interfaces;

import com.example.Reto_Final.model.Amount;
import com.example.Reto_Final.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<User> Crear(User user);
    Mono<User> ObtenerUsuarioPorId(@PathVariable("userId") String userId);
    Mono<User> ActualizarBalanceUsuario(@PathVariable("userId") String userId, @RequestBody Amount amount);
    Mono<User> ActualizarBalance(@PathVariable("userId") String userId, @RequestBody Integer amount);
}
