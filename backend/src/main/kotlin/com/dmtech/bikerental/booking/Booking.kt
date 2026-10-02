package com.dmtech.bikerental.booking

import com.dmtech.bikerental.bike.Bike
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
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

    @Column(nullable = false, unique = true)
    var uuid: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bike_id", nullable = false)
    var bike: Bike,
    var createdAt: Instant = Instant.now(),
    var bookingStart: OffsetDateTime = OffsetDateTime.now(),
    var bookingEnd: OffsetDateTime? = null,
    var bookedBy: String
) {
    override fun equals(other: Any?): Boolean {
        return other is Booking && other.uuid == this.uuid;
    }

    override fun hashCode(): Int {
        return this.uuid.hashCode();
    }
}
