package com.pat.fizzbuzz_gateway.service

import com.pat.dto.web.FizzBuzzRequestDto
import com.pat.dto.web.FizzBuzzResponseDto
import com.pat.dto.web.FizzBuzzResultResponseDto
import java.security.Principal
import java.util.*

interface FizzBuzzService {
    fun fizzBuzzCreateRequest(request: FizzBuzzRequestDto, principal: Principal): FizzBuzzResponseDto
    fun fizzBuzzGetResult(ticket: UUID, principal: Principal): FizzBuzzResultResponseDto?
}