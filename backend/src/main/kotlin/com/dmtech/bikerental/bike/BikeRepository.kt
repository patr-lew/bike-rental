package com.dmtech.bikerental.bike

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.time.OffsetDateTime
import java.util.UUID

interface BikeRepository: JpaRepository<Bike, Long> {

    @Query("""
    select new com.dmtech.bikerental.bike.BikeOverviewDto(
        bike.uuid,
        bike.manufacturer,
        bike.rimSize,
        bike.frameSize,
        bike.color,
        case when booking.id is not null then true else false end,
        booking.bookedBy
    )
    from Bike bike
    left join Booking booking
        on booking.bike = bike
        and booking.bookingStart <= :now
        and booking.bookingEnd is null
""")
    fun findAllBikesWithAvailability(now: OffsetDateTime): List<BikeOverviewDto>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findBikeByUuid(uuid: UUID): Bike?
}