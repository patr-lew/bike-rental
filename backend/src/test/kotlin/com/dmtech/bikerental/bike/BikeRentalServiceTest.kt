package com.dmtech.bikerental.bike

import com.dmtech.bikerental.BikeRentalApplication
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(classes = [BikeRentalApplication::class])
class BikeRentalServiceTest {

    @MockitoBean
    private lateinit var bikeRepository: BikeRepository

    private lateinit var bikeService: BikeService

    @BeforeEach
    fun setUp() {
        bikeService = BikeService(bikeRepository)
    }

    @Test
    fun `getAllBikes returns all bikes`() {
        val bikes = listOf(Bike(), Bike())
        val bikeOverviews = bikes.map(this::bikeAsBikeOverview)
        whenever(bikeRepository.findAllBikesWithAvailability(any())).thenReturn(bikeOverviews)

        val result = bikeService.getAllBikes()

        assertEquals(bikeOverviews, result)
    }

    private fun bikeAsBikeOverview(bike: Bike): BikeOverviewDto {
        return BikeOverviewDto(
            uuid = bike.uuid,
            manufacturer = bike.manufacturer,
            rimSize = bike.rimSize,
            frameSize = bike.frameSize,
            color = bike.color,
            rented = false,
            rentedBy = null
        )
    }
}