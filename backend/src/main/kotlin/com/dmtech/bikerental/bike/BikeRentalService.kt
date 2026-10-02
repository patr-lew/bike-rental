package com.dmtech.bikerental.bike

import org.springframework.stereotype.Service

@Service
class BikeRentalService(var bikeRepository: BikeRepository) {

    /**
     * Retrieves all bikes from the repository.
     *
     * @return A list of all bikes.
     */
    fun getAllBikes(): List<Bike> {
        return bikeRepository.findAll()
    }
}