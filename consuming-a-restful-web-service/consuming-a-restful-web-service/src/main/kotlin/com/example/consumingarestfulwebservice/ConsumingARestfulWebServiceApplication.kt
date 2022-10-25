package com.example.consumingarestfulwebservice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
@SpringBootApplication
@EnableConfigurationProperties(Properties::class)
class ConsumingARestfulWebServiceApplication(
	private val properties: Properties
) {
	val log: Logger = LoggerFactory.getLogger(ConsumingARestfulWebServiceApplication::class.java)

	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

	@Bean
	fun run(restTemplate: RestTemplate): CommandLineRunner = CommandLineRunner {
		val movie = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/550?api_key=${properties.key}", Movie::class.java
		)
		log.info(movie.toString())
	}
}

fun main(args: Array<String>) {
	runApplication<ConsumingARestfulWebServiceApplication>(*args)
}