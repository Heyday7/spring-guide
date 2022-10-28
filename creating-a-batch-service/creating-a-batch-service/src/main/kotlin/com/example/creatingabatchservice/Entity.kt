package com.example.creatingabatchservice

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Person(
     var firstName: String,
     var lastName: String,
     @Id @GeneratedValue
     val id: Int? = null
)