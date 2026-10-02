package com.dmtech.bikerental.bike

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "bikes")
 class Bike (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bike_seq")
    @SequenceGenerator(
       name = "bike_seq",
       sequenceName = "bike_seq",
       allocationSize = 1
    )
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var uuid: UUID = UUID.randomUUID(),

    var manufacturer: String = "",
    var rimSize: Int = 0,
    var frameSize: Int = 0,
    var color: String = ""
) {
   override fun equals(other: Any?): Boolean {
      return other is Bike && other.uuid == this.uuid;
   }

   override fun hashCode(): Int {
      return this.uuid.hashCode();
   }
 }
