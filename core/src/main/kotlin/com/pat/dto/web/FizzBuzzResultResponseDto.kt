package com.pat.dto.web

import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class FizzBuzzResultResponseDto(
    val result: String,
    val inputNumber: Int,
    val ticket: UUID,
    val requestCreatedAt: OffsetDateTime,
    val requestCompletedAt: OffsetDateTime?,
    val status: FizzBuzzStatus,
)
