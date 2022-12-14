package com.example.schedulingtasks

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class ScheduledTasks {
    val log: Logger = LoggerFactory.getLogger(ScheduledTasks::class.java)
    val dateFormat = SimpleDateFormat("HH:mm:ss")

    @Scheduled(fixedRate = 5000)
    fun reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(Date()))
    }
}