package com.pat.fizzbuzz_logging_service.web.controller

import com.pat.dto.web.LogRequestDto
import com.pat.dto.web.LogResponseDto
import com.pat.fizzbuzz_logging_service.service.LogRestService
import com.pat.types.LogOrderDirection
import com.pat.types.LogOrderType
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/log")
class LogController(private val logRestService: LogRestService) {

    @GetMapping
    fun getLogs(
        @RequestParam(required = false) user: String?,
        @RequestParam(required = false) ticket: UUID?,
        @RequestParam(required = false) orderBy: String?,
        @RequestParam(required = false) direction: String?,
    ): ResponseEntity<LogResponseDto> {
        val reqDto = LogRequestDto(
            user,
            ticket,
            orderBy?.let { LogOrderType.entries.firstOrNull { it.value == orderBy } } ?: LogOrderType.DATE,
            direction?.let { LogOrderDirection.entries.firstOrNull { it.value == direction } }
                ?: LogOrderDirection.DESC,
        )
        return ResponseEntity(logRestService.getLogs(reqDto), HttpStatus.OK)
    }
}