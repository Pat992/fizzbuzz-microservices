package com.pat.dto.commands

import java.util.*

interface BaseCommand {
    val user: String
    val inputNumber: Int
    val ticket: UUID
    val packageName: String
}