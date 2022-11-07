package com.example.tutorialrest

import com.fasterxml.jackson.annotation.JsonCreator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Employee(
    val firstName: String,
    val lastName: String,
    val role: String,
    @Id @GeneratedValue
    val id: Long? = null
) {

    @JsonCreator constructor(
        name: String,
        role: String,
        id: Long? = null
    ) : this(
        name.split(" ").getOrElse(0) { "" },
        name.split(" ").getOrElse(1) { "" },
        role,
        id
    )

    val name = "$firstName $lastName"
}