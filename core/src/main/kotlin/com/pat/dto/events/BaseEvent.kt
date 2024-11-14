package com.pat.dto.events

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
