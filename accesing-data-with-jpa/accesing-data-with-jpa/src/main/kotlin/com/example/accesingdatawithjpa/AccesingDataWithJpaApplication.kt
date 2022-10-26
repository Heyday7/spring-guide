package com.example.accesingdatawithjpa

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.findByIdOrNull

@SpringBootApplication
class AccesingDataWithJpaApplication {
	val log: Logger = LoggerFactory.getLogger(AccesingDataWithJpaApplication::class.java)

	@Bean
	fun demo(repository: CustomerRepository) = CommandLineRunner {
		// save a few customers
		repository.save(Customer("Jack", "Bauer"))
		repository.save(Customer("Chloe", "O'Brian"))
		repository.save(Customer("Kim", "Bauer"))
		repository.save(Customer("David", "Palmer"))
		repository.save(Customer("Michelle", "Dessler"))

		// fetch all customers
		// fetch all customers
		log.info("Customers found with findAll():")
		log.info("-------------------------------")
		for (customer in repository.findAll()) {
			log.info(customer.toString())
		}
		log.info("")

		// fetch an individual customer by ID
		// fetch an individual customer by ID
		val customer: Customer? = repository.findByIdOrNull(1L)
		log.info("Customer found with findById(1L):")
		log.info("--------------------------------")
		log.info(customer.toString())
		log.info("")

		// fetch customers by last name
		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):")
		log.info("--------------------------------------------")
		repository.findByLastName("Bauer").forEach { bauer -> log.info(bauer.toString()) }
		// for (Customer bauer : repository.findByLastName("Bauer")) {
		//  log.info(bauer.toString());
		// }
		// for (Customer bauer : repository.findByLastName("Bauer")) {
		//  log.info(bauer.toString());
		// }
		log.info("")
	}
}

fun main(args: Array<String>) {
	runApplication<AccesingDataWithJpaApplication>(*args)
}
