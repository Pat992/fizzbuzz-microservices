package com.pat.dto.commands

import java.util.*

interface BaseCommand {
    val userId: Int
    val inputNumber: Int
    val ticket: UUID
}