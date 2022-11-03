package com.example.eurekaclient

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class EurekaClientApplication

fun main(args: Array<String>) {
	runApplication<EurekaClientApplication>(*args)
}

@RestController
class ServiceInstanceRestController(
		@Autowired private val discoveryClient: DiscoveryClient
) {
	@RequestMapping("/service-instances/{applicationName}")
	fun serviceInstancesByApplicationName(
			@PathVariable applicationName: String
	): List<ServiceInstance> {
		return discoveryClient.getInstances(applicationName)
	}
}