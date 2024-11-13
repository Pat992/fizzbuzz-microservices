package com.pat.dto.commands

import java.util.*

data class FizzBuzzTransformCommand(
    override val ticket: UUID,
    override val user: String,
    override val inputNumber: Int,
) : BaseCommand
