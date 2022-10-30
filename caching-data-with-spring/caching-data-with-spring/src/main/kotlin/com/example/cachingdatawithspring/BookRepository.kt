package com.example.cachingdatawithspring

interface BookRepository {
    fun getByIsbn(isbn: String) : Book
}