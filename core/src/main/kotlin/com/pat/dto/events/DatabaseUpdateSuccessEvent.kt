package com.pat.dto.events

import java.util.*

data class DatabaseUpdateSuccessEvent(
    override val ticket: UUID,
    override val userId: Int,
    override val inputNumber: Int,
) : BaseEvent
