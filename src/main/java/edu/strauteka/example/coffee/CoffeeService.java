package edu.strauteka.example.coffee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public Mono<Coffee> findByCoffeeName(String coffeeName) {
        return coffeeRepository.findByCoffee(coffeeName);
    }

    public Flux<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Mono<Coffee> findCoffeeById(Long id) {
        return coffeeRepository.findById(id);
    }

    public Flux<Coffee> findSimilar(String somethingSimilar) {
        log.info("Find something like: {}", somethingSimilar);
        return coffeeRepository.findLikeCoffee("%" + somethingSimilar + "%");
    }

    @Transactional
    public Mono<Coffee> updateCoffeePrice(Long id, Double price) {
        return coffeeRepository.findById(id).map(coffee -> {
            coffee.setPrice(price);
            return coffee;
        }).flatMap(coffeeRepository::save);
    }

    public Mono<Coffee> saveCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @Transactional
    public Mono<Void> deleteCoffeeById(Long id) {
        return coffeeRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return coffeeRepository.deleteAll();
    }

    @Transactional
    public Mono<Void> addMoneyWherePriceLower(Double conditionalPrice, Double addMoney) {
        return coffeeRepository.addPriceWherePriceLower(conditionalPrice, addMoney);
    }
}
