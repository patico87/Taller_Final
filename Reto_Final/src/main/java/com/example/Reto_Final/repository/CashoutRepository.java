package com.example.Reto_Final.repository;

import com.example.Reto_Final.model.Cashout;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CashoutRepository  extends ReactiveCrudRepository<Cashout, String> {
    Flux<Cashout> findAllByUserId(String id);
}
