package com.example.tutorialrest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController(
    @Autowired private val repository: EmployeeRepository,
    @Autowired private val assembler: EmployeeModelAssembler
) {
    private val log = LoggerFactory.getLogger(EmployeeController::class.java)

    @GetMapping("/employees")
    fun all(): CollectionModel<EntityModel<Employee>> {
        val employees = repository.findAll()
            .map(assembler::toModel)

        return CollectionModel.of(
            employees,
            linkTo<EmployeeController> { methodOn(EmployeeController::class.java).all() }.withSelfRel())
    }

    @PostMapping("/employees")
    fun newEmployee(@RequestBody newEmployee: Employee): ResponseEntity<EntityModel<Employee>> {
        log.info(newEmployee.toString())

        val entityModel = assembler.toModel(repository.save(newEmployee))
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel)
    }

    @GetMapping("/employees/{id}")
    fun one(@PathVariable id: Long): EntityModel<Employee> {
        val employee = repository.findById(id)
            .orElseThrow { EmployeeNotFoundException(id) }
        return assembler.toModel(employee)
    }

    @PutMapping("/employees/{id}")
    fun replaceEmployee(
        @RequestBody newEmployee: Employee,
        @PathVariable id: Long
    ): ResponseEntity<EntityModel<Employee>> {
        val updatedEmployee = repository.findById(id)
            .map { employee ->
                val changedEmployee = employee.copy(
                    firstName = newEmployee.firstName,
                    lastName= newEmployee.lastName,
                    role = newEmployee.role
                )
                repository.save(changedEmployee)
            }
            .orElseGet {
                repository.save(newEmployee.copy(id = id))
            }
        val entityModel = assembler.toModel(updatedEmployee)
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel)
    }

    @DeleteMapping("/employees/{id}")
    fun deleteEmployee(@PathVariable id: Long) {
        repository.deleteById(id)
    }
}