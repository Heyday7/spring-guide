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
    fun initDatabase(
            employeeRepository: EmployeeRepository,
            orderRepository: OrderRepository
        ): CommandLineRunner = CommandLineRunner {
        employeeRepository.save(Employee("Heyday", "7",  "man"))
        employeeRepository.save(Employee("Snuupy", "ho", "player"))

        employeeRepository.findAll().forEach { employee -> log.info("Preloaded $employee") }

        orderRepository.save(Order("MacBook Pro", Status.COMPLETED))
        orderRepository.save(Order("iPhone", Status.IN_PROGRESS))

        orderRepository.findAll().forEach { order ->
            log.info("Preloaded $order")
        }
    }
}