package com.dmtech.bikerental.booking

import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class BookingRequestDto (
    val bikeId: UUID,

    @field:NotBlank
    val userName: String
)