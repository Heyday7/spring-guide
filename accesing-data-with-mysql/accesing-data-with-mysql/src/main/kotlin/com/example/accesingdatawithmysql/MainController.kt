package com.example.accesingdatawithmysql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/demo")
class MainController(
        @Autowired private val userRepository: UserRepository
) {
    @PostMapping("/add")
    fun addNewUser(
            @RequestParam name: String,
            @RequestParam email: String
    ): String {
        val n = User(
                name = name,
                email = email
        )
        userRepository.save(n)
        return "Saved"
    }

    @GetMapping("/all")
    fun getAllUsers(): Iterable<User> {
        return userRepository.findAll()
    }
}