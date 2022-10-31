package com.example.enablingcorsforarestfulwebservice

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Greeting(
    val content: String,
    @Id @GeneratedValue
    val id: Long? = null
)