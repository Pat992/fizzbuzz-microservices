package com.pat.dto.commands

import com.pat.dto.events.DatabaseUpdateFailedEvent
import com.pat.dto.events.DatabaseUpdateSuccessEvent
import com.pat.types.DatabaseTypes
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
    val type: DatabaseTypes
) : BaseCommand


fun DatabaseUpdateCommand.toDatabaseUpdateFailedEvent(
    packageName: String,
    eventCreatedAt: OffsetDateTime,
    message: String,
) = DatabaseUpdateFailedEvent(
    this.ticket,
    this.user,
    this.inputNumber,
    packageName,
    eventCreatedAt,
    message,
    this.result,
    this.type,
)

fun DatabaseUpdateCommand.toDatabaseUpdateSuccessEvent(
    packageName: String,
    eventCreatedAt: OffsetDateTime,
    message: String,
    result: String
) = DatabaseUpdateSuccessEvent(
    this.ticket,
    this.user,
    this.inputNumber,
    packageName,
    eventCreatedAt,
    message,
    result,
    this.type,
)