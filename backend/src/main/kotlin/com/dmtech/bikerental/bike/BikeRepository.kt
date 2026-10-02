package com.dmtech.bikerental.bike

import org.springframework.data.jpa.repository.JpaRepository

interface BikeRepository: JpaRepository<Bike, Long>
