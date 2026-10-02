package com.dmtech.bikerental.booking

import com.dmtech.bikerental.bike.Bike
import com.dmtech.bikerental.bike.BikeNotAvailableException
import com.dmtech.bikerental.bike.BikeNotFoundException
import com.dmtech.bikerental.bike.BikeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BookingService(val bikeRepository: BikeRepository, val bookingRepository: BookingRepository) {

    @Transactional
    fun bookBikeIfAvailable(bikeId: UUID, username: String): BookingResultDto {
        val chosenBike = bikeRepository.findBikeByUuid(bikeId) ?: throw BikeNotFoundException(bikeId.toString())
        if (doesActiveBookingExist(chosenBike)) {
            throw BikeNotAvailableException(bikeId.toString())
        }

        val booking = Booking(bike = chosenBike, bookedBy = username)
        bookingRepository.save(booking)

        return BookingResultDto(booking.uuid, bikeId, chosenBike.manufacturer, username, )
    }

    private fun doesActiveBookingExist(bike: Bike): Boolean {
        return bookingRepository.existsByBikeAndBookingEndIsNull(bike)
    }
}