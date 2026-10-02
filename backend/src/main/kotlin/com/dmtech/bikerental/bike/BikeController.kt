package com.dmtech.bikerental.bike

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/bikes")
class BikeController(val bikeService: BikeService) {

    @GetMapping
    fun getBikes(): List<BikeDto> {
        return bikeService.getAllBikes()
    }

}