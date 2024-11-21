package com.pat.fizzbuzz_logging_service.service

import com.pat.dto.web.LogRequestDto
import com.pat.dto.web.LogResponseDto

interface LogRestService {
    fun getLogs(logRequestDto: LogRequestDto): LogResponseDto
}