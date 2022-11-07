package com.example.tutorialrest

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component

@Component
class EmployeeModelAssembler: RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    override fun toModel(employee: Employee): EntityModel<Employee> {
        return EntityModel.of(employee,
            linkTo<EmployeeController> {
                employee.id?.let {
                    methodOn(EmployeeController::class.java).one(it)
                }
            }.withSelfRel(),
            linkTo<EmployeeController> {
                methodOn(EmployeeController::class.java).all()
            }.withRel("employees")
            )
    }
}