package com.dmtech.bikerental.bike

class BikeNotFoundException(bikeId: String): RuntimeException("Bike not found - bikeId $bikeId")