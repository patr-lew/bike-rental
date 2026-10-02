package com.dmtech.bikerental.bike

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.Instant
import java.time.OffsetDateTime

interface BikeRepository: JpaRepository<Bike, Long> {


    @Query("""
        select
            bike.uuid as uuid,
            bike.manufacturer as manufacturer,
            bike.rimSize as rimSize,
            bike.frameSize as frameSize,
            bike.color as color,
            case when booking.id is not null then true else false end as rented,
            booking.bookedBy as rentedBy
        from Bike bike
        left join Booking booking
            on booking.bike = bike
            and booking.bookingStart <= :now
            and booking.bookingEnd is null
        """
    )
    fun findAllBikesWithAvailability(now: OffsetDateTime): List<BikeDto>
}