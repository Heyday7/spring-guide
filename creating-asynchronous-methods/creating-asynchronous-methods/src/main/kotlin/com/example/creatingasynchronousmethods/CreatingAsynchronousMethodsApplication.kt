package com.example.creatingasynchronousmethods

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.client.RestTemplate
import java.util.concurrent.Executor

@SpringBootApplication
@EnableAsync
class CreatingAsynchronousMethodsApplication {
	@Bean
	fun restTemplate(): RestTemplate {
		return RestTemplate()
	}

	@Bean
	fun taskExecutor(): Executor {
		val executor = ThreadPoolTaskExecutor()
		executor.corePoolSize = 2
		executor.maxPoolSize = 2
		executor.queueCapacity = 500
		executor.setThreadNamePrefix("GithubLookup-")
		executor.initialize()
		return executor
	}
}

fun main(args: Array<String>) {
	runApplication<CreatingAsynchronousMethodsApplication>(*args).close()
}
