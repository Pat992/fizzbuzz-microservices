package com.pat.fizzbuzz_logging_service.service

import com.pat.dto.commands.LogCreateCommand

interface LogCommandService {
    fun saveLog(logCreateCommand: LogCreateCommand)
}