package com.pat.dto.web

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class FizzBuzzRequestDto(
    @NotNull
    @Positive
    val number: Int
)
