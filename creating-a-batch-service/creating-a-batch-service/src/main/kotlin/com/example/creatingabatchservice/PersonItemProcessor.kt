package com.example.creatingabatchservice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class PersonItemProcessor : ItemProcessor<Person, Person> {
    private val log: Logger = LoggerFactory.getLogger(PersonItemProcessor::class.java)

    @Override
    override fun process(person: Person) = kotlin.run {
        val firstName = person.firstName.uppercase()
        val lastName = person.lastName.uppercase()
        val transformedPerson = Person(firstName, lastName)

        log.info("Converting ($person) into ($transformedPerson)")

        transformedPerson
    }
}