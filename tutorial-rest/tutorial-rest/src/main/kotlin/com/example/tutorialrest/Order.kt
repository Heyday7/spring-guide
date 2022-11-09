package com.example.tutorialrest

import kotlinx.serialization.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Serializable
@Entity
@Table(name = "CUSTOMER_ORDER")
data class Order(
        val description: String,
        val status: Status,
        @Id @GeneratedValue
        val id: Long? = null
)