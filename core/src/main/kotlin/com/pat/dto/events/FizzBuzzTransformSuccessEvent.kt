package com.pat.dto.events

import java.time.OffsetDateTime
import java.util.*

data class FizzBuzzTransformSuccessEvent(
    override val ticket: UUID,
    override val user: String,
    override val inputNumber: Int,
    override val packageName: String,
    override val eventCreatedAt: OffsetDateTime,
    override val message: String,
    val result: String,
) : BaseEvent