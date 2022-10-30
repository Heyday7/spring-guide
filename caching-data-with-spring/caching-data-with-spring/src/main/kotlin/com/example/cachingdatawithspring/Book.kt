package com.example.cachingdatawithspring

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Book(
    val isbn: String,
    val title: String,
    @Id @GeneratedValue
    val id: Int? = null
)