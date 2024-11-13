package com.pat.dto.web

import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class LogDto(
    val ticket: UUID,
    val user: String,
    val inputNumber: Int,
    val status: FizzBuzzStatus,
    val message: String,
    val updatedAt: OffsetDateTime,
)