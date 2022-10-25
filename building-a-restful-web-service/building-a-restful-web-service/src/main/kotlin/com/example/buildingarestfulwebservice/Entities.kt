package com.example.buildingarestfulwebservice

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Greeting(
    val content: String,
    @Id @GeneratedValue
    val id: Long
)