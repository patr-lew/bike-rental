package com.dmtech.bikerental.booking

import com.dmtech.bikerental.bike.Bike
import org.springframework.data.jpa.repository.JpaRepository

interface BookingRepository: JpaRepository<Booking, Long> {

    fun existsByBikeAndBookingEndIsNull(bike: Bike): Boolean
}