package com.example.Reto_Final.controller;

import com.example.Reto_Final.services.interfaces.IPaymentRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

@RestController
@RequestMapping("/auxiliar")
public class AuxiliarController {
    @Autowired
    private IPaymentRestClient paymentRestClient;

    String Estado = "Approved";

    @PostExchange("/auxiliar")
    public String RealizaPago(@PathVariable("userId") String userId, @PathVariable("amount") Integer amount){
        return  Estado;
    }
}
