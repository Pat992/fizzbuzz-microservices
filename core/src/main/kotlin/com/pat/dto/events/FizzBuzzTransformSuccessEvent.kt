package com.pat.dto.events

import java.util.*

data class FizzBuzzTransformSuccessEvent(
    override val ticket: UUID,
    override val userId: Int,
    override val inputNumber: Int,
    val result: String,
) : BaseEvent
