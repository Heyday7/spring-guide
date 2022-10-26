package com.example.buildinganapplicationwithspringboot

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import java.util.Arrays

@SpringBootApplication
class BuildingAnApplicationWithSpringBootApplication {
	@Bean
	fun commandLineRunner(ctx: ApplicationContext) = CommandLineRunner {
		println("Let's inspect the beans provided by Spring Boot:")
		val beanNames = ctx.beanDefinitionNames
		Arrays.sort(beanNames)
		for (name in beanNames) {
			println(name)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<BuildingAnApplicationWithSpringBootApplication>(*args)
}
