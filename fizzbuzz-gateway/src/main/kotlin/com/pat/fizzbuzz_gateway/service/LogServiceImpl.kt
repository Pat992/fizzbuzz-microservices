package com.pat.fizzbuzz_gateway.service

import com.pat.dto.web.LogDto
import com.pat.dto.web.LogResponseDto
import com.pat.types.FizzBuzzStatus
import com.pat.types.LogOrderType
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

@Service
class LogServiceImpl : LogService {
    override fun getLogs(user: String?, ticket: UUID?, orderBy: LogOrderType): LogResponseDto =
        LogResponseDto(
            listOf(
                LogDto(UUID.randomUUID(), "user", 1, "", FizzBuzzStatus.COMPLETED, "", OffsetDateTime.now())
            )
        )
}