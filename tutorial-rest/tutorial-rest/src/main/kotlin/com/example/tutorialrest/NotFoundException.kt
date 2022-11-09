package com.example.tutorialrest

class EmployeeNotFoundException(
    private val id: Long
): RuntimeException() {
    override val message: String
        get() = "Could not find employee $id"
}

class OrderNotFoundException(
        private val id: Long
): RuntimeException() {
    override val message: String
        get() = "Could not find order $id"
}