package com.pat.dto.events

import java.util.*

interface BaseEvent {
    val userId: Int
    val inputNumber: Int
    val ticket: UUID
}
