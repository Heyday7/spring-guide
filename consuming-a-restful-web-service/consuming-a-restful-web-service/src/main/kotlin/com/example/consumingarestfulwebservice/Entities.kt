package com.example.consumingarestfulwebservice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class Movie(
        val adult: Boolean,
        @OneToMany val genres: List<Genre>,
        @Id val id: Long? = null
)

@Entity
data class Genre(
        @Id val id: Long,
        val name: String
)