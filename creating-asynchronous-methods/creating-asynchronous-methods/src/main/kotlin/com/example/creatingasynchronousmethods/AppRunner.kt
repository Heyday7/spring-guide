package com.example.creatingasynchronousmethods

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AppRunner(
        @Autowired private val gitHubLookupService: GitHubLookupService
): CommandLineRunner {
    private val logger = LoggerFactory.getLogger(AppRunner::class.java)

    override fun run(vararg args: String?) {
        val start = System.currentTimeMillis()

        val page1 = gitHubLookupService.findUser("PivotalSoftware")
        val page2 = gitHubLookupService.findUser("CloudFoundry")
        val page3 = gitHubLookupService.findUser("Spring-Projects")

        CompletableFuture.allOf(page1, page2, page3).join()

        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
    }
}