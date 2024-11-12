package com.pat.dto.commands

import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class LogCreateCommand(
    override val ticket: UUID,
    override val userId: Int,
    override val inputNumber: Int,
    val status: FizzBuzzStatus,
    val message: String,
    val updatedAt: OffsetDateTime,
) : BaseCommand
