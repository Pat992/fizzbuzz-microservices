package com.pat.dto.commands

import java.util.*

data class FizzBuzzTransformCommand(
    override val ticket: UUID,
    override val userId: Int,
    override val inputNumber: Int,
) : BaseCommand
