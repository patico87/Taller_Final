package com.example.Reto_Final.controller;

import com.example.Reto_Final.model.Amount;
import com.example.Reto_Final.model.User;
import com.example.Reto_Final.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping
    public Mono<User> Crear(@RequestBody User user) {
        return iUserService.Crear(user);
    }

    @GetMapping("/{id}")
    public Mono<User> ObtenerUsuarioPorId(@PathVariable String id) {
        return iUserService.ObtenerUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    public Mono<User> ActualizarBalanceUsuario(@PathVariable String id, @RequestBody Amount amount) {
        return iUserService.ActualizarBalanceUsuario(id,amount);

    }

}
