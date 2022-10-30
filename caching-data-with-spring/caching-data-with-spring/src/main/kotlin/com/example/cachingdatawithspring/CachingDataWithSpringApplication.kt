package com.example.cachingdatawithspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class CachingDataWithSpringApplication

fun main(args: Array<String>) {
	runApplication<CachingDataWithSpringApplication>(*args)
}
