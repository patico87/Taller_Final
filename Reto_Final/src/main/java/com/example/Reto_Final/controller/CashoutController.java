package com.example.Reto_Final.controller;

import com.example.Reto_Final.model.Cashout;
import com.example.Reto_Final.model.User;
import com.example.Reto_Final.services.interfaces.ICashoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cashouts")
public class CashoutController {

    @Autowired
    private ICashoutService iCashoutService;

    @PostMapping
    public Mono<Cashout> Crear(@RequestBody Cashout cashout) {
        return iCashoutService.Crear(cashout);
    }

    @GetMapping("/user/{userId}")
    public Flux<Cashout> ObtenerRetirosPorusuario(@PathVariable String userId) {
        return iCashoutService.ObtenerRetirosPorusuario(userId);
    }


}
