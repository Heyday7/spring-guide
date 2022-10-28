package com.example.accesingdatawithmysql

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(
    val name: String,
    val email: String,
    @Id @GeneratedValue
    val id: Int? = null
)