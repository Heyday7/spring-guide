package com.example.buildingareactiverestfulwebservice

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BuildingAReactiveRestfulWebServiceApplication {

	@Bean
	fun startRunner(
			greetingClient: GreetingClient
	): CommandLineRunner = CommandLineRunner {
		println(">> message = " + greetingClient.getMessage().block())
	}
}

fun main(args: Array<String>) {
	runApplication<BuildingAReactiveRestfulWebServiceApplication>(*args)
}
