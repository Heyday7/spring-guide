package com.example.user

import org.springframework.cloud.client.DefaultServiceInstance
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import reactor.core.publisher.Flux

class SayHelloConfiguration {

    @Bean
    @Primary
    fun serviceInstanceListSupplier(): ServiceInstanceListSupplier {
        return DemoServiceInstanceListSuppler("say-hello")
    }
}

class DemoServiceInstanceListSuppler(
        private val serviceId: String
): ServiceInstanceListSupplier {

    override fun getServiceId(): String {
        return serviceId
    }

    override fun get(): Flux<MutableList<ServiceInstance>> {
        return Flux.just(
                mutableListOf(
                        DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8090, false),
                        DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 9092, false),
                        DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 9999, false),
                )
        )
    }
}