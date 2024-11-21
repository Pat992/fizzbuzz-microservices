package com.pat.fizzbuzz_database_service.service

import com.pat.dto.web.FizzBuzzResultResponseDto
import java.util.*

interface ResultService {
    fun getResultByTicketAndUsername(ticket: UUID, username: String): FizzBuzzResultResponseDto?
}