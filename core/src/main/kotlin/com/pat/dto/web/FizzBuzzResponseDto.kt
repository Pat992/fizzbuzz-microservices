package com.pat.dto.web

import java.time.OffsetDateTime
import java.util.*

data class FizzBuzzResponseDto(
    val ticket: UUID,
    val requestCreatedAt: OffsetDateTime
)
