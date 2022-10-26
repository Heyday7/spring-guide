package com.example.buildinganapplicationwithspringboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/")
    fun index(): String {
        return "Greeting from Spring Boot!"
    }
}