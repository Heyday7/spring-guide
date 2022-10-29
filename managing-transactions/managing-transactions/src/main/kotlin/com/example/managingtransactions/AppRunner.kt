package com.example.managingtransactions

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class AppRunner(
        @Autowired private val bookingService: BookingService
): CommandLineRunner {
    private val logger = LoggerFactory.getLogger(AppRunner::class.java)

    override fun run(vararg args: String?) {
        bookingService.book("Alice", "Bob", "Carol")
        Assert.isTrue(bookingService.findAllBookings().size == 3,
        "First booking should work with no problem")
        try {
            bookingService.book("Chris", "Samuel")
        } catch (e: RuntimeException) {
            logger.info("v--- The following exception is expect because 'Samuel' is too " +
                    "big for the DB ---v")
            logger.error(e.message)
        }

        for (person in bookingService.findAllBookings()) {
            logger.info("So far, $person is booked.")
        }
        logger.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
                "and Chris was rolled back in the same TX")
        Assert.isTrue(bookingService.findAllBookings().size == 3,
                "'Samuel' should have triggered a rollback")

        try {
            bookingService.book("Buddy", null)
        } catch (e: RuntimeException) {
            logger.info("v--- The following exception is expect because null is not " +
                    "valid for the DB ---v")
            logger.error(e.message)
        }

        for (person in bookingService.findAllBookings()) {
            logger.info("So far, $person is booked.")
        }
        logger.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
                "Buddy was rolled back in the same TX")
        Assert.isTrue(bookingService.findAllBookings().size == 3,
                "'null' should have triggered a rollback")

    }
}