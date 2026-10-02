package com.dmtech.bikerental.bike

import com.dmtech.bikerental.BikeRentalApplication
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(classes = [BikeRentalApplication::class])
class BikeRentalServiceTest {

    @MockitoBean
    private lateinit var bikeRepository: BikeRepository

    private lateinit var bikeRentalService: BikeRentalService

    @BeforeEach
    fun setUp() {
        bikeRentalService = BikeRentalService(bikeRepository)
    }

    @Test
    fun `getAllBikes returns all bikes`() {
        val bikes = listOf(Bike(), Bike())
        `when`(bikeRepository.findAll()).thenReturn(bikes)

        val result = bikeRentalService.getAllBikes()

        assertEquals(bikes, result)
    }
}