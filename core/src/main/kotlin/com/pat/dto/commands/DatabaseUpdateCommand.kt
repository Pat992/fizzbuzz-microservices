package com.pat.dto.commands

import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class DatabaseUpdateCommand(
    override val ticket: UUID,
    override val user: String,
    override val inputNumber: Int,
    val result: String?,
    val requestCreatedAt: OffsetDateTime,
    val requestCompletedAt: OffsetDateTime?,
    val status: FizzBuzzStatus,
) : BaseCommand

