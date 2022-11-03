package com.example.messagingwithredis

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
class Receiver: MessageListener {
    private val logger: Logger = LoggerFactory.getLogger(Receiver::class.java)
    private val counter = AtomicInteger()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        logger.info("Received <$message> by onMessage")
        counter.incrementAndGet()
    }

    fun getCount(): Int {
        return counter.get()
    }
}