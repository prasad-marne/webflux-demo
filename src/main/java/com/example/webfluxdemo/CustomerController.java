package com.example.webfluxdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerRepository repository;

	@GetMapping("/customers/{lastName}")
	Mono<Customer> namesByLastname(@PathVariable String lastName) {
		return Mono.just(repository.findByLastName(lastName));
	}

	@GetMapping(value = "/customers", produces = "application/stream+json")
	Flux<Customer> findAll() {
		return Flux.fromIterable(repository.findAll());
	}

}
