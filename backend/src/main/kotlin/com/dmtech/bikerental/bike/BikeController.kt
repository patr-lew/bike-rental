package com.dmtech.bikerental.bike

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/bikes")
class BikeController(val bikeService: BikeService) {
    private val logger = LoggerFactory.getLogger(BikeController::class.java)

    @GetMapping
    fun getBikes(): List<BikeOverviewDto> {
        logger.debug("Requesting all bike overviews")
        return bikeService.getAllBikes()
    }

}