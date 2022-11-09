package com.example.tutorialrest

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component

@Component
class OrderModelAssembler: RepresentationModelAssembler<Order, EntityModel<Order>> {
    override fun toModel(entity: Order): EntityModel<Order> {
        val orderModel = EntityModel.of(entity,
            linkTo<OrderController> {
                entity.id?.let { methodOn(OrderController::class.java).one(it) }
            }.withSelfRel(),
            linkTo<OrderController> {
                methodOn(OrderController::class.java).all()
            }.withRel("orders")
        )

        if (entity.status == Status.IN_PROGRESS) {
            orderModel.add(
                linkTo<OrderController> {
                    entity.id?.let { methodOn(OrderController::class.java).cancel(it) }
                }.withRel("cancel")
            )
            orderModel.add(
                linkTo<OrderController> {
                    entity.id?.let { methodOn(OrderController::class.java).complete(it) }
                }.withRel("complete")
            )
        }

        return orderModel
    }
}