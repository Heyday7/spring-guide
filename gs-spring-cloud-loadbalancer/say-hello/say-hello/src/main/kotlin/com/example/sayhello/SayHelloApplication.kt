package com.example.sayhello

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
@SpringBootApplication
class SayHelloApplication {
	private val logger = LoggerFactory.getLogger(SayHelloApplication::class.java)

	@GetMapping("/greeting")
	fun greet(): String {
		logger.info("Access /greeting")
		val greetings  = listOf("Hi there", "Greetings", "Salutations")
		val rand = Random.Default
		val randomNum = rand.nextInt(greetings.size)
		return greetings[randomNum]
	}

	@GetMapping("/")
	fun home(): String {
		logger.info("Access /")
		return "Hi!"
	}
}

fun main(args: Array<String>) {
	runApplication<SayHelloApplication>(*args)
}
