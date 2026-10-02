package com.dmtech.bikerental

import com.dmtech.bikerental.bike.BikeNotAvailableException
import com.dmtech.bikerental.bike.BikeNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(BikeNotFoundException::class)
    fun handleBikeNotFound(exception: BikeNotFoundException): ProblemDetail =
        ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND, exception.message
        ).apply {
            title = "Bike not found"
        }

    @ExceptionHandler(BikeNotAvailableException::class)
    fun handleBikeNotAvailable(exception: BikeNotAvailableException): ProblemDetail =
        ProblemDetail.forStatusAndDetail(
            HttpStatus.CONFLICT, exception.message
        ).apply {
            title = "Bike not available"
        }
}