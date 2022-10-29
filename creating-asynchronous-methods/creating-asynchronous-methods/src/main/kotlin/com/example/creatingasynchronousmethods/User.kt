package com.example.creatingasynchronousmethods

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class User(
    var name: String?,
    var Blog: String?,
    @Id @GeneratedValue
    val id: Int? = null
)