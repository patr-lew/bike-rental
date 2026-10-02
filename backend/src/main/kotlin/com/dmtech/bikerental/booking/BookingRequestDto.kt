package com.dmtech.bikerental.booking

import java.util.UUID

data class BookingRequestDto (
    val bikeId: UUID,
    val userName: String
)