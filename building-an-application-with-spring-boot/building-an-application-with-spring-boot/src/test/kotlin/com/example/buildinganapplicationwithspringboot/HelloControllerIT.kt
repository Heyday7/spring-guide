package com.example.buildinganapplicationwithspringboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIT(@Autowired val template: TestRestTemplate) {

    @Test
    fun getHello() {
        val response = template.getForEntity<String>("/")
        assertThat(response.body?.equals("Greeting from Spring Boot!") ?: false)
    }
}