package com.pat.dto.events

import com.pat.dto.commands.DatabaseUpdateCommand
import com.pat.dto.commands.LogCreateCommand
import com.pat.types.DatabaseTypes
import com.pat.types.FizzBuzzStatus
import java.time.OffsetDateTime
import java.util.*

interface BaseEvent {
    val ticket: UUID
    val user: String
    val inputNumber: Int
    val packageName: String
    val eventCreatedAt: OffsetDateTime
    val message: String
}

fun BaseEvent.toLogCreateCommand(fizzBuzzStatus: FizzBuzzStatus): LogCreateCommand = LogCreateCommand(
    this.ticket,
    this.user,
    this.inputNumber,
    this.packageName,
    fizzBuzzStatus,
    this.message,
    this.eventCreatedAt
)

fun BaseEvent.toDatabaseUpdateCommand(
    result: String?,
    requestCompletedAt: OffsetDateTime?,
    status: FizzBuzzStatus,
    type: DatabaseTypes
) = DatabaseUpdateCommand(
    this.ticket,
    this.user,
    this.inputNumber,
    this.packageName,
    result,
    this.eventCreatedAt,
    requestCompletedAt,
    status,
    type
)
