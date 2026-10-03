package com.dmtech.bikerental.booking

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/bookings")
class BookingController(val bookingService: BookingService) {

    @PostMapping
    fun bookABike(@RequestBody request: BookingRequestDto): ResponseEntity<BookingResultDto> {
        val (bikeId, username) = request

        val createdBooking = bookingService.bookBikeIfAvailable(bikeId, username)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{bookingId}")
            .buildAndExpand(createdBooking.uuid)
            .toUri()

        return ResponseEntity
            .created(location)
            .body(createdBooking)
    }

}
