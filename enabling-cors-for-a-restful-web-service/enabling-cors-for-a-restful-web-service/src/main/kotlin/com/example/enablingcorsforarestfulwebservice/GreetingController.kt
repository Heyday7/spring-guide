package com.example.enablingcorsforarestfulwebservice

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {
    private val template = "Hello, %s!"

    @CrossOrigin(origins = ["http://localhost:8080"])
    @GetMapping("/greeting")
    fun greeting(
            @RequestParam(required = false, defaultValue = "World") name: String
    ): Greeting {
        println("====== get greeting ======")
        return Greeting(String.format(template, name))
    }

    @GetMapping("/greeting-javaconfig")
    fun greetingWithJavaconfig(
            @RequestParam(required = false, defaultValue = "World") name: String
    ): Greeting {
        println("====== get greeting ======")
        return Greeting(String.format(template, name))
    }
}