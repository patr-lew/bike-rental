package com.dmtech.bikerental.booking

import java.util.UUID

data class BookingResultDto(
    val uuid: UUID,
    val bikeId: UUID,
    val bikeManufacturer: String,
    val bookedBy: String,
)
