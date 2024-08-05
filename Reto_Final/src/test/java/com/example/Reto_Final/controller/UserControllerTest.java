
package com.example.Reto_Final.controller;

import com.example.Reto_Final.model.User;
import com.example.Reto_Final.services.interfaces.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserController.class})
@WebFluxTest(UserController.class)
public class UserControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private IUserService userService;

    @Test
    void testCrearUser() {
        var user = new User();
        user.setName("Luis");
        user.setBalance(100);

        when(userService.Crear(refEq(user))).thenReturn(Mono.just(user));

        webTestClient.post()
                .uri("/users")
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Luis")
                .jsonPath("$.balance").isEqualTo(100);
    }

    @Test
    void testObtenerUsuarioPorId_Found() {
        User user = new User();
        user.setId("1");
        user.setBalance(100);

        when(userService.ObtenerUsuarioPorId("1")).thenReturn(Mono.just(user));

        webTestClient.get()
                .uri("/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .isEqualTo(user);
    }

    @Test
    void testObtenerUsuarioPorId_NotFound() {
        when(userService.ObtenerUsuarioPorId(("1"))).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/users/1")
                .exchange()
                .expectStatus().isNotFound();
    }


}

