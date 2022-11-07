package com.example.tutorialrest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDatabase {
    val log: Logger = LoggerFactory.getLogger(LoadDatabase::class.java)

    @Bean
    fun initDatabase(repository: EmployeeRepository): CommandLineRunner = CommandLineRunner {
        log.info("Preloading " + repository.save(Employee("Heyday", "7",  "man")))
        log.info("Preloading " + repository.save(Employee("Snuupy", "ho", "player")))
        log.info("Preloading " + repository.save(Employee("Snu upy", "player")))
    }
}