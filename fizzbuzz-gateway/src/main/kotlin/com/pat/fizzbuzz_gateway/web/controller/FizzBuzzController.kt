package com.pat.fizzbuzz_gateway.web.controller

import com.pat.dto.web.FizzBuzzRequestDto
import com.pat.dto.web.FizzBuzzResponseDto
import com.pat.dto.web.FizzBuzzResultResponseDto
import com.pat.fizzbuzz_gateway.service.FizzBuzzService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@RequestMapping("/api/v1/fizzbuzz")
class FizzBuzzController(private val fizzBuzzService: FizzBuzzService) {

    @PostMapping
    fun fizzBuzzCreateRequest(
        @Valid @RequestBody request: FizzBuzzRequestDto,
        principal: Principal,
    ): ResponseEntity<FizzBuzzResponseDto> =
        ResponseEntity(fizzBuzzService.fizzBuzzCreateRequest(request, principal), HttpStatus.CREATED)

    @GetMapping("{ticket}")
    fun fizzBuzzGetResult(
        @PathVariable("ticket") ticket: UUID,
        principal: Principal,
    ): ResponseEntity<FizzBuzzResultResponseDto?> = fizzBuzzService.fizzBuzzGetResult(ticket, principal).let {
        ResponseEntity(it, if (it == null) HttpStatus.NOT_FOUND else HttpStatus.OK)
    }
}