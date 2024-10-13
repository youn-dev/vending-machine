package com.protopie.protopieapp.aop

import com.protopie.protopieapp.helper.CustomException
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<Any> {
        logger.warn("${e.message}", e)
        return ResponseEntity(
            e.message,
            null,
            HttpStatus.BAD_REQUEST
        )
    }
}
