package com.pat.dto.commands

import com.pat.dto.events.FizzBuzzTransformFailedEvent
import com.pat.dto.events.FizzBuzzTransformSuccessEvent
import java.time.OffsetDateTime
import java.util.*

data class FizzBuzzTransformCommand(
    override val ticket: UUID,
    override val user: String,
    override val inputNumber: Int,
    override val packageName: String,
) : BaseCommand

fun FizzBuzzTransformCommand.toFizzBuzzTransformSuccessEvent(
    eventCreatedAt: OffsetDateTime,
    message: String,
    result: String,
    packageName: String,
) = FizzBuzzTransformSuccessEvent(
    this.ticket,
    this.user,
    this.inputNumber,
    packageName,
    eventCreatedAt,
    message,
    result,
)

fun FizzBuzzTransformCommand.toFizzBuzzTransformFailedEvent(
    eventCreatedAt: OffsetDateTime,
    message: String,
    packageName: String,
) = FizzBuzzTransformFailedEvent(
    this.ticket,
    this.user,
    this.inputNumber,
    packageName,
    eventCreatedAt,
    message,
)