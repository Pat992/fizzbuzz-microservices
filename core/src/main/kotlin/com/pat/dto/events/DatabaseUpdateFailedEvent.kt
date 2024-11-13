package com.pat.dto.events

import java.util.*

data class DatabaseUpdateFailedEvent(
    override val ticket: UUID,
    override val user: String,
    override val inputNumber: Int,
    val errorMessage: String,
) : BaseEvent


