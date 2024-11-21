package com.pat.dto.web

import com.pat.types.LogOrderDirection
import com.pat.types.LogOrderType
import java.util.*

data class LogRequestDto(
    val user: String?,
    val ticket: UUID?,
    val orderBy: LogOrderType = LogOrderType.DATE,
    val orderDirection: LogOrderDirection = LogOrderDirection.DESC,
)