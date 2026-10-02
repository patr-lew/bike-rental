package com.dmtech.bikerental.booking

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/bookings")
class BookingController(val bookingService: BookingService) {

    @PostMapping
    fun bookABike(@RequestBody request: BookingRequestDto): ResponseEntity<BookingResultDto> {
        val (bikeId, username) = request

        val createdBooking = bookingService.bookBikeIfAvailable(bikeId, username)

        return ResponseEntity
            .created(URI.create("/booking/$createdBooking"))
            .body(createdBooking)
    }

}