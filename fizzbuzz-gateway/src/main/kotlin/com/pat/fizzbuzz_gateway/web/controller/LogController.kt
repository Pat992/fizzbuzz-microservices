package com.pat.fizzbuzz_gateway.web.controller

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
class LogController {

    @GetMapping
    fun getLogs(
        @RequestParam(required = false) user: String?,
        @RequestParam(required = false) ticket: UUID?,
        @RequestParam(required = false) orderBy: LogOrderType = LogOrderType.DATE
    ): ResponseEntity<Unit> = ResponseEntity(HttpStatus.OK)
}