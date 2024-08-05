package com.example.Reto_Final.services;

import com.example.Reto_Final.exceptions.UserNotFoundException;
import com.example.Reto_Final.model.Amount;
import com.example.Reto_Final.model.User;
import com.example.Reto_Final.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import reactor.test.StepVerifier;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearUser() {
        User user = new User();
        user.setId("1");
        user.setBalance(100);

        when(userRepository.save(any(User.class))).thenReturn(Mono.just(user));

        StepVerifier.create(userService.Crear(user))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void testObtenerUsuarioPorId_Found() {
        User user = new User();
        user.setId("1");
        user.setBalance(100);

        when(userRepository.findById(eq("1"))).thenReturn(Mono.just(user));

        StepVerifier.create(userService.ObtenerUsuarioPorId("1"))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void testObtenerUsuarioPorId_NotFound() {
        when(userRepository.findById(eq("1"))).thenReturn(Mono.empty());

        StepVerifier.create(userService.ObtenerUsuarioPorId("1"))
                .expectError(UserNotFoundException.class)
                .verify();
    }

    @Test
    void testActualizarBalanceUsuario() {
        User user = new User();
        user.setId("1");
        user.setBalance(100);

        Amount amount = new Amount();
        amount.setAmount(50);

        when(userRepository.findById(eq("1"))).thenReturn(Mono.just(user));
        when(userRepository.save(any(User.class))).thenReturn(Mono.just(user));

        StepVerifier.create(userService.ActualizarBalanceUsuario("1", amount))
                .expectNextMatches(updatedUser -> updatedUser.getBalance() == 150)
                .verifyComplete();
    }

    @Test
    void testActualizarBalance() {
        User user = new User();
        user.setId("1");
        user.setBalance(100);

        Integer amount = 50;

        when(userRepository.findById(eq("1"))).thenReturn(Mono.just(user));
        when(userRepository.save(any(User.class))).thenReturn(Mono.just(user));

        StepVerifier.create(userService.ActualizarBalance("1", amount))
                .expectNextMatches(updatedUser -> updatedUser.getBalance() == 50)
                .verifyComplete();
    }
}
