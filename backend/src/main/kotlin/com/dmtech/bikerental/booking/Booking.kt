package com.dmtech.bikerental.booking

import com.dmtech.bikerental.bike.Bike
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.Instant
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "bookings")
class Booking (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_seq", allocationSize = 1 )
    var id: Long? = null,
    var uuid: UUID = UUID.randomUUID(),

    @OneToOne(fetch = FetchType.LAZY)
    var bike: Bike? = null,
    var createdAt: Instant = Instant.now(),
    var bookingStart: OffsetDateTime = OffsetDateTime.now(),
    var bookingEnd: OffsetDateTime? = null,
    var bookedBy: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        return other is Booking && other.uuid == this.uuid;
    }

    override fun hashCode(): Int {
        return this.uuid.hashCode();
    }
}
