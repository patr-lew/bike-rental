package com.dmtech.bikerental.booking

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/bookings")
class BookingController(val bookingService: BookingService) {
    private val logger = LoggerFactory.getLogger(BookingController::class.java)

    @PostMapping
    fun bookABike(@Valid @RequestBody request: BookingRequestDto): ResponseEntity<BookingResultDto> {
        val (bikeId, username) = request
        logger.info("Received request to book a bike with id {}", bikeId)

        val createdBooking = bookingService.bookBikeIfAvailable(bikeId, username)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdBooking)
    }

}
