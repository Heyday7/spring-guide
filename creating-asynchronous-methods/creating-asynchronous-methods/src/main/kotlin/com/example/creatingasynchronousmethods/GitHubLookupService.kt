package com.example.creatingasynchronousmethods

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Service
class GitHubLookupService(
        @Autowired private val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(GitHubLookupService::class.java)

    @Async
    fun findUser(user: String): CompletableFuture<User> {
        logger.info("Looking up $user")
        val url = String.format("https://api.github.com/users/%s", user)
        val result = restTemplate.getForObject(url, User::class.java)
        Thread.sleep(1000L)
        return CompletableFuture.completedFuture(result)
    }
}