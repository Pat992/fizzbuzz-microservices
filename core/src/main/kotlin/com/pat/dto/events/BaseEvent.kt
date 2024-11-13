package com.pat.dto.events

import java.util.*

interface BaseEvent {
    val user: String
    val inputNumber: Int
    val ticket: UUID
}
