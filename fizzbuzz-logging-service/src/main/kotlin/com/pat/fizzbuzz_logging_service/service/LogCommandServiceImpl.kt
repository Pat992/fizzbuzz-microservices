package com.pat.fizzbuzz_logging_service.service

import com.pat.dto.commands.LogCreateCommand
import com.pat.fizzbuzz_logging_service.domain.toLogEntity
import com.pat.fizzbuzz_logging_service.repository.LogRepository
import org.springframework.stereotype.Service

@Service
class LogCommandServiceImpl(private val logRepository: LogRepository) : LogCommandService {
    override fun saveLog(logCreateCommand: LogCreateCommand) {
        logRepository.save(logCreateCommand.toLogEntity())
    }
}