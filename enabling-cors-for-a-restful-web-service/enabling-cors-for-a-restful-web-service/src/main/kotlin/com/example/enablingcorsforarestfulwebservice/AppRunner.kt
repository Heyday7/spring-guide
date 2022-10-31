package com.example.enablingcorsforarestfulwebservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class AppRunner(
        @Autowired private val restTemplate: RestTemplate
): CommandLineRunner {
    override fun run(vararg args: String?) {
        try {
            val test1 = restTemplate.getForObject("http://localhost:8080/greeting", Greeting::class.java)
            val test2 = restTemplate.getForObject("http://localhost:8080/greeting-javaconfig", Greeting::class.java)
            println("$test1, $test2")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}