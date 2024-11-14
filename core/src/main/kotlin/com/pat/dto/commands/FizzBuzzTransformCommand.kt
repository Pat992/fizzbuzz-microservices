package com.pat.dto.commands

import java.util.*

data class FizzBuzzTransformCommand(
    val ticket: UUID,
    val user: String,
    val inputNumber: Int,
)
