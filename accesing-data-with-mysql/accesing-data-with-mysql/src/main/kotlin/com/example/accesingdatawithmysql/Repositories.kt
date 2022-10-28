package com.example.accesingdatawithmysql

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int>
