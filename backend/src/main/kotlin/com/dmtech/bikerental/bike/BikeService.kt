package com.dmtech.bikerental.bike

import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class BikeService(val bikeRepository: BikeRepository) {

    /**
     * Retrieves all bikes from the repository.
     *
     * @return A list of all bikes.
     */
    fun getAllBikes(): List<BikeOverviewDto> {
        return bikeRepository.findAllBikesWithAvailability(OffsetDateTime.now())
    }
}