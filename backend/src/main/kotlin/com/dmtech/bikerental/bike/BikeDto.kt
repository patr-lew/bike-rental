package com.dmtech.bikerental.bike

import java.util.UUID

data class BikeDto(
    val uuid: UUID,
    val manufacturer: String,
    val rimSize: Int,
    val frameSize: Int,
    val color: String,
    val isRented: Boolean,
    val rentedBy: String?
)
