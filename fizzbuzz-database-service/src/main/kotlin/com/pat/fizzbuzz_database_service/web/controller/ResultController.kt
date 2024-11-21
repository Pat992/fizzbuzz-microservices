package com.pat.fizzbuzz_database_service.web.controller

import com.pat.dto.web.FizzBuzzResultResponseDto
import com.pat.fizzbuzz_database_service.service.ResultService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/result")
class ResultController(private val resultService: ResultService) {
    @GetMapping("{ticket}/{username}")
    fun getResult(
        @PathVariable("ticket") ticket: UUID,
        @PathVariable("username") username: String
    ): ResponseEntity<FizzBuzzResultResponseDto?> = resultService.getResultByTicketAndUsername(ticket, username).let {
        ResponseEntity(it, if (it == null) HttpStatus.NOT_FOUND else HttpStatus.OK)
    }
}