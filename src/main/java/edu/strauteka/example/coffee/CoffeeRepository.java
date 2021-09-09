package edu.strauteka.example.coffee;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, Long> {
    Mono<Coffee> findByCoffee(String coffee);

    @Query("SELECT * FROM COFFEE c where UPPER(c.coffee) like UPPER(:similar)")
    Flux<Coffee> findLikeCoffee(String similar);

    @Query("UPDATE COFFEE C set C.price = C.price + :addPrice where c.price < :conditionalPrice")
    Mono<Void> addPriceWherePriceLower(Double conditionalPrice, Double addPrice);

}
