package com.example.accessingjpadatawithrest

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Person(
        val firstName: String,
        val lastName: String,
        @Id @GeneratedValue
        val id: Long? = null
)