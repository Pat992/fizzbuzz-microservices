package com.pat.dto.events

import java.util.*

data class FizzBuzzTransformFailedEvent(
    override val ticket: UUID,
    override val userId: Int,
    override val inputNumber: Int,
    val errorMessage: String,
) : BaseEvent
