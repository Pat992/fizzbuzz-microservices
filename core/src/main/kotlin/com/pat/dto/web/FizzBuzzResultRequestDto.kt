package com.pat.dto.web

import jakarta.validation.constraints.NotEmpty
import java.util.*

data class FizzBuzzResultRequestDto(
    @NotEmpty
    val ticket: UUID
)
