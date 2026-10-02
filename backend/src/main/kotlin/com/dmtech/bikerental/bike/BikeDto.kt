package com.dmtech.bikerental.bike

import java.util.UUID

data class BikeDto(
    val uuid: UUID,
    val manufacturer: String,
    val rimSize: Int,
    val frameSize: Int,
    val color: String,
    val rented: Boolean,
    val rentedBy: String?
)
