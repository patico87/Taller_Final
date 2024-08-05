package com.example.Reto_Final.services;

import com.example.Reto_Final.exceptions.UserNotFoundException;
import com.example.Reto_Final.model.Amount;
import com.example.Reto_Final.model.User;
import com.example.Reto_Final.repository.UserRepository;
import com.example.Reto_Final.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> Crear(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> ObtenerUsuarioPorId(String id) {
        return userRepository.findById(id)
         .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado")));
    }

    @Override
    public Mono<User> ActualizarBalanceUsuario(String id, Amount amount)
    {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setBalance(existingUser.getBalance() + amount.getAmount());
                    return userRepository.save(existingUser);
                });
    }

    @Override
    public Mono<User> ActualizarBalance(String id, Integer amount)
    {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setBalance(existingUser.getBalance() - amount);
                    return userRepository.save(existingUser);
                });
    }
}
