package com.example.messagingwithredis

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import kotlin.system.exitProcess

@SpringBootApplication
class MessagingWithRedisApplication {
	@Bean
	fun container(
			connectionFactory: RedisConnectionFactory,
			receiver: Receiver
	): RedisMessageListenerContainer {
		val container = RedisMessageListenerContainer()
		container.setConnectionFactory(connectionFactory)
		container.addMessageListener(receiver, PatternTopic("chat"))
		return container
	}

	@Bean
	fun template(connectionFactory: RedisConnectionFactory): StringRedisTemplate {
		return StringRedisTemplate(connectionFactory)
	}
}

fun main(args: Array<String>) {
	val logger: Logger = LoggerFactory.getLogger("main")


	val ctx = runApplication<MessagingWithRedisApplication>(*args)
	val template = ctx.getBean(StringRedisTemplate::class.java)
	val receiver = ctx.getBean(Receiver::class.java)

	while (receiver.getCount() == 0) {
		logger.info("Sending message...")
		template.convertAndSend("chat", "Hello from Redis!")
		Thread.sleep(500L)
	}

	exitProcess(0)
}
