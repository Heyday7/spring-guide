package com.example.tutorialrest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.mediatype.problem.Problem
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.function.Supplier


@RestController
class OrderController(
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val assembler: OrderModelAssembler
) {

    @GetMapping("/orders")
    fun all(): CollectionModel<EntityModel<Order>> {
        val orders = orderRepository.findAll().map(assembler::toModel)

        return CollectionModel.of(
                orders,
                linkTo<OrderController> { methodOn(OrderController::class.java).all() }.withSelfRel()
        )
    }

    @GetMapping("/orders/{id}")
    fun one(@PathVariable id: Long): EntityModel<Order> {
        val order = orderRepository.findById(id).orElseThrow()

        return assembler.toModel(order)
    }

    @PostMapping("/orders")
    fun newOrder(@RequestBody order: Order): ResponseEntity<EntityModel<Order>> {
        val newOrder = orderRepository.save(order.copy(status = Status.IN_PROGRESS))
        return ResponseEntity //
                .created(
                        linkTo<OrderController> { newOrder.id?.let { methodOn(OrderController::class.java).one(it) } }.toUri()) //
                .body(assembler.toModel(newOrder))
    }

    @DeleteMapping("/orders/{id}/cancel")
    fun cancel(@PathVariable id: Long): ResponseEntity<*>? {
        val order = orderRepository.findById(id) //
                .orElseThrow { OrderNotFoundException(id) }
        if (order.status === Status.IN_PROGRESS) {
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order.copy(status = Status.CANCELLED))))
        }
        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't cancel an order that is in the " + order.status + " status"))
    }

    @PutMapping("/orders/{id}/complete")
    fun complete(@PathVariable id: Long): ResponseEntity<*>? {
        val order = orderRepository.findById(id) //
                .orElseThrow { OrderNotFoundException(id) }
        if (order.status === Status.IN_PROGRESS) {
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order.copy(status = Status.COMPLETED))))
        }
        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't complete an order that is in the " + order.status + " status"))
    }
}