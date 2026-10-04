package com.dmtech.bikerental

import com.dmtech.bikerental.bike.Bike
import com.dmtech.bikerental.bike.BikeRepository
import com.dmtech.bikerental.booking.BookingRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.UUID

@SpringBootTest
class BikeRentalApiIntegrationTest {


    @Autowired
    private lateinit var applicationContext: WebApplicationContext

    @Autowired
    private lateinit var bikeRepository: BikeRepository

    @Autowired
    private lateinit var bookingRepository: BookingRepository

    private lateinit var mockMvc: MockMvc
    private lateinit var bike: Bike

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(applicationContext)
            .defaultRequest<DefaultMockMvcBuilder>(get("/").servletPath("/bikerental"))
            .build()
        bike = testBike()
        bookingRepository.deleteAllInBatch()
        bikeRepository.deleteAllInBatch()

        bikeRepository.save(bike)
    }

    @Test
    fun `booking a bike persists it and updates bike availability`() {
        // given
        val userName = "Mark"

        // when
        bookBike(bike.uuid.toString(), userName)
            .andExpect(status().isCreated)
            .andReturn()

        val savedBooking = bookingRepository.findAll().single()

        // then
        assertEquals(userName, savedBooking.bookedBy)

        mockMvc.perform(get("/bikerental/bikes"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].uuid").value(bike.uuid.toString()))
            .andExpect(jsonPath("$[0].rented").value(true))
            .andExpect(jsonPath("$[0].rentedBy").value(userName))
    }

    @Test
    fun `booking an already rented bike returns conflict without another booking`() {
        // given
        bookBike(bike.uuid.toString(), "Alice")

            // verify
            .andExpect(status().isCreated)
        assertEquals(1, bookingRepository.count())

        // when
        bookBike(bike.uuid.toString(), "Alice")

            // then
            .andExpect(status().isConflict)
        assertEquals(1, bookingRepository.count())
    }

    @Test
    fun `booking an unknown bike returns not found`() {
        // given
        val unknownBikeId = UUID.randomUUID().toString()

        // when
        bookBike(unknownBikeId, "Frank")

            // then
            .andExpect(status().isNotFound)

        assertEquals(0, bookingRepository.count())
    }

    @Test
    fun `booking with malformed bike id returns bad request`() {
        // given
        val malformedBikeId = "not-uuid"

        // when
        bookBike(malformedBikeId, "Frank")

            // then
            .andExpect(status().isBadRequest)
        assertEquals(0, bookingRepository.count())
    }

    @Test
    fun `booking with blank user name returns bad request`() {
        // given
        val blankUserName = ""

        // when
        bookBike(bike.uuid.toString(), blankUserName)

            // then
            .andExpect(status().isBadRequest)
        assertEquals(0, bookingRepository.count())
    }

    private fun bookBike(bikeId: String, username: String): ResultActions {
        return mockMvc.perform(
            post("/bikerental/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookingRequest(bikeId, username))
        )
    }

    private fun bookingRequest(bikeId: String, username: String) =
        """{"bikeId":"$bikeId","userName":"$username"}"""

    private fun testBike() = Bike(
        manufacturer = "Integration Bike",
        rimSize = 28,
        frameSize = 54,
        color = "Black"
    )
}
