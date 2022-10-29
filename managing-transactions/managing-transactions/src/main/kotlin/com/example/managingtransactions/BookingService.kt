package com.example.managingtransactions

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class BookingService(
        @Autowired private val jdbcTemplate: JdbcTemplate
) {
    private val logger = LoggerFactory.getLogger(BookingService::class.java)

    @Transactional
    fun book(vararg persons: String?) {
        for (person in persons) {
            logger.info("Booking $person in a seat...")
            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person)
            logger.info(
                    jdbcTemplate.query(
                            "select FIRST_NAME from BOOKINGS") { rs, _ ->
                        rs.getString("FIRST_NAME")
                    }.toString()
            )
        }
    }

    fun findAllBookings(): List<String> {
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS") { rs, _ ->
            rs.getString("FIRST_NAME")
        }
    }
}