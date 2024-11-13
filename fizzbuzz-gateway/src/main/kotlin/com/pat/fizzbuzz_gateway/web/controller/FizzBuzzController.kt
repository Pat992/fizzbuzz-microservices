package com.pat.fizzbuzz_gateway.web.controller

import com.pat.dto.web.FizzBuzzRequestDto
import com.pat.dto.web.FizzBuzzResponseDto
import com.pat.dto.web.FizzBuzzResultResponseDto
import com.pat.types.FizzBuzzStatus
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.OffsetDateTime
import java.util.*

@RestController
@RequestMapping("/api/v1/fizzbuzz")
class FizzBuzzController {

    @PostMapping
    fun fizzBuzzCreateRequest(
        @Valid @RequestBody request: FizzBuzzRequestDto,
        principal: Principal
    ): ResponseEntity<FizzBuzzResponseDto> {
        return ResponseEntity(FizzBuzzResponseDto(UUID.randomUUID(), OffsetDateTime.now()), HttpStatus.CREATED)
    }

    @GetMapping("{ticket}")
    fun fizzBuzzGetResult(
        @PathVariable("ticket") ticket: UUID,
        principal: Principal
    ): ResponseEntity<FizzBuzzResultResponseDto> =
        ResponseEntity(
            FizzBuzzResultResponseDto(
                "1",
                1,
                ticket,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                FizzBuzzStatus.COMPLETED,
                false,
                null
            ), HttpStatus.OK
        )
}