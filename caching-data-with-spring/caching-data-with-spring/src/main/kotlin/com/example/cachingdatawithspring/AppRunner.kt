package com.example.cachingdatawithspring

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class AppRunner(
        @Autowired private val bookRepository: BookRepository
) : CommandLineRunner {
    val logger: Logger = LoggerFactory.getLogger(AppRunner::class.java)

    override fun run(vararg args: String?) {
        logger.info(".... Fetching books")
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"))
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"))
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
    }
}