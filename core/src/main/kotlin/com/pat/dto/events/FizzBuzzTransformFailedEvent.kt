package com.pat.dto.events

import com.pat.dto.commands.DatabaseUpdateCommand
import com.pat.dto.commands.LogCreateCommand
import com.pat.types.DatabaseTypes
import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class FizzBuzzTransformFailedEvent(
    val ticket: UUID,
    val user: String,
    val inputNumber: Int,
    val packageName: String,
    val eventCreatedAt: OffsetDateTime,
    val message: String,
)

fun FizzBuzzTransformFailedEvent.toLogCreateCommand(fizzBuzzStatus: FizzBuzzStatus): LogCreateCommand =
    LogCreateCommand(
        this.ticket,
        this.user,
        this.inputNumber,
        this.packageName,
        fizzBuzzStatus,
        this.message,
        this.eventCreatedAt
    )

fun FizzBuzzTransformFailedEvent.toDatabaseUpdateCommand(
    result: String?,
    requestCompletedAt: OffsetDateTime?,
    status: FizzBuzzStatus,
    type: DatabaseTypes
) = DatabaseUpdateCommand(
    this.ticket,
    this.user,
    this.inputNumber,
    result,
    this.eventCreatedAt,
    requestCompletedAt,
    status,
    type
)