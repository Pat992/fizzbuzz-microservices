package com.pat.dto.commands

import com.pat.types.DatabaseTypes
import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class DatabaseUpdateCommand(
    val ticket: UUID,
    val user: String,
    val inputNumber: Int,
    val result: String?,
    val requestCreatedAt: OffsetDateTime,
    val requestCompletedAt: OffsetDateTime?,
    val status: FizzBuzzStatus,
    val type: DatabaseTypes
)