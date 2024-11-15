package com.pat.dto.commands

import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class LogCreateCommand(
    override val ticket: UUID,
    override val user: String,
    override val inputNumber: Int,
    override val packageName: String,
    val status: FizzBuzzStatus,
    val message: String,
    val updatedAt: OffsetDateTime,
) : BaseCommand