package com.pat.fizzbuzz_gateway.service

import com.pat.dto.web.LogResponseDto
import com.pat.types.LogOrderType
import java.util.*

interface LogService {
    fun getLogs(user: String?, ticket: UUID?, orderBy: LogOrderType): LogResponseDto
}