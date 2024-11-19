package com.pat.fizzbuzz_logging_service.repository

import com.pat.fizzbuzz_logging_service.domain.LogEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LogRepository : JpaRepository<LogEntity, Int> {
    fun findByUser(user: String): List<LogEntity>
    fun findByTicket(ticket: UUID): List<LogEntity>
}