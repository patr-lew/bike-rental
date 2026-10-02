package com.dmtech.bikerental.bike

class BikeNotAvailableException(bikeId: String): RuntimeException("Bike not available: bikeId $bikeId")