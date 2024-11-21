package com.pat.fizzbuzz_gateway.service

import com.pat.dto.web.LogRequestDto
import com.pat.dto.web.LogResponseDto

interface LogService {
    fun getLogs(logRequestDto: LogRequestDto): LogResponseDto?
}