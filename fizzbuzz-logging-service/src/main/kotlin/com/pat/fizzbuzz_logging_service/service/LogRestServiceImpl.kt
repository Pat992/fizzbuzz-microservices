package com.pat.fizzbuzz_logging_service.service

import com.pat.dto.web.LogRequestDto
import com.pat.dto.web.LogResponseDto
import com.pat.fizzbuzz_logging_service.domain.toLogResponseDto
import com.pat.fizzbuzz_logging_service.repository.LogRepository
import org.springframework.stereotype.Service

@Service
class LogRestServiceImpl(private val logRepository: LogRepository) : LogRestService {
    override fun getLogs(logRequestDto: LogRequestDto): LogResponseDto {
        val res =
            if (logRequestDto.user != null) logRepository.findByUser(
                logRequestDto.user!!,
                logRequestDto.orderBy.value,
                logRequestDto.orderDirection.value
            )
            else if (logRequestDto.ticket != null) logRepository.findByTicket(
                logRequestDto.ticket!!,
                logRequestDto.orderBy.value,
                logRequestDto.orderDirection.value
            )
            else logRepository.findAll(logRequestDto.orderBy.value, logRequestDto.orderDirection.value)

        return res.toLogResponseDto()
    }
}