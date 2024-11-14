package com.pat.dto.events

import com.pat.dto.commands.LogCreateCommand
import com.pat.types.DatabaseTypes
import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

data class DatabaseUpdateFailedEvent(
    val ticket: UUID,
    val user: String,
    val inputNumber: Int,
    val packageName: String,
    val eventCreatedAt: OffsetDateTime,
    val message: String,
    val result: String,
    val type: DatabaseTypes,
)

fun DatabaseUpdateFailedEvent.toLogCreateCommand(fizzBuzzStatus: FizzBuzzStatus): LogCreateCommand = LogCreateCommand(
    this.ticket,
    this.user,
    this.inputNumber,
    this.packageName,
    fizzBuzzStatus,
    this.message,
    this.eventCreatedAt
)