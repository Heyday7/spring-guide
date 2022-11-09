package com.example.tutorialrest

import com.fasterxml.jackson.annotation.JsonCreator
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Serializable
@Entity
data class Employee(
    val firstName: String,
    val lastName: String,
    val role: String,
    @Id @GeneratedValue
    val id: Long? = null
)


