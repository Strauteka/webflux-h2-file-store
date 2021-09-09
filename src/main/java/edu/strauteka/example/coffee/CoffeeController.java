package edu.strauteka.example.coffee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/coffee", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoffeeController {
    private final CoffeeService coffeeService;

    @GetMapping
    Flux<Coffee> findAll() {
        return coffeeService.findAll();
    }

    @GetMapping(value = "id/{id}")
    Mono<Coffee> findCoffeeById(@PathVariable("id") Long id) {
        return coffeeService.findCoffeeById(id);
    }

    @GetMapping(value = "name/{coffeeName}")
    Mono<Coffee> findByCoffeeName(@PathVariable("coffeeName") String coffeeName) {
        return coffeeService.findByCoffeeName(coffeeName);
    }

    @GetMapping(value = "like/{coffeeName}")
    Flux<Coffee> findSimilar(@PathVariable("coffeeName") String coffeeName) {
        return coffeeService.findSimilar(coffeeName);
    }

    @PutMapping("id/{id}/{price}")
    Mono<Coffee> updateCoffeePrice(@PathVariable("id") Long id,
                                   @PathVariable("price") Double price) {
        return coffeeService.updateCoffeePrice(id, price).switchIfEmpty(Mono.error(new CoffeeNotFoundException()));
    }

    @PutMapping("{conditionalPrice}/{addMoney}")
    Mono<Void> updateCoffeePrice(@PathVariable("conditionalPrice") Double conditionalPrice,
                                   @PathVariable("addMoney") Double addMoney) {
        return coffeeService.addMoneyWherePriceLower(conditionalPrice, addMoney);
    }

    @PostMapping
    Mono<Coffee> saveCoffeePrice(@RequestBody Coffee coffee) {
        return coffeeService.saveCoffee(coffee);
    }

    @DeleteMapping("{id}")
    Mono<Void> deleteById(@PathVariable("id")Long id) {
        return coffeeService.deleteCoffeeById(id);
    }

    @DeleteMapping
    Mono<Void> deleteAll() {
        return coffeeService.deleteAll();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Coffee Not Found")
    private static class CoffeeNotFoundException extends Exception {
    }
}
