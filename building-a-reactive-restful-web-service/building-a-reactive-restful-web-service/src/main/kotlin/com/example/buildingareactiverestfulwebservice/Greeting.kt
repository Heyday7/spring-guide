package com.example.buildingareactiverestfulwebservice

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Greeting(
        val message: String,
        @Id @GeneratedValue
        val id: Int? = null
)