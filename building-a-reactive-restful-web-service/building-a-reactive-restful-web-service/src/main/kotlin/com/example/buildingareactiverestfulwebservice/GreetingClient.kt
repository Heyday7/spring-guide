package com.example.buildingareactiverestfulwebservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class GreetingClient {
    private val client: WebClient = WebClient.create("http://localhost:8080")

    fun getMessage(): Mono<String> {
        return client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting::class.java)
                .map(Greeting::message)
    }
}