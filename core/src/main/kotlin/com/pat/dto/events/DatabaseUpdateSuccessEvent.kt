package com.pat.dto.events

import java.util.*

data class DatabaseUpdateSuccessEvent(
    override val ticket: UUID,
    override val user: String,
    override val inputNumber: Int,
) : BaseEvent
