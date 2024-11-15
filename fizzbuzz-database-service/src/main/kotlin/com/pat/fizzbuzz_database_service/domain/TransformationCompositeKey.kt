package com.pat.fizzbuzz_database_service.domain

import java.io.Serializable
import java.util.*

class TransformationCompositeKey(
    val user: String,
    val ticket: UUID,
) : Serializable