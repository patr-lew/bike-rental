package com.dmtech.bikerental.bike

import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class BikeRentalService(var bikeRepository: BikeRepository) {

    /**
     * Retrieves all bikes from the repository.
     *
     * @return A list of all bikes.
     */
    fun getAllBikes(): List<BikeDto> {
        return bikeRepository.findAllBikesWithAvailability(OffsetDateTime.now())
    }
}