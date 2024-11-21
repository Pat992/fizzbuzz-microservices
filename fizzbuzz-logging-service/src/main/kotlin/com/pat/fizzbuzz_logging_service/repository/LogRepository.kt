package com.pat.fizzbuzz_logging_service.repository

import com.pat.fizzbuzz_logging_service.domain.LogEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface LogRepository : JpaRepository<LogEntity, Int> {
    @Query("SELECT * FROM logs ORDER BY ?1 ?2", nativeQuery = true)
    fun findAll(orderBy: String, orderType: String): List<LogEntity>

    @Query("SELECT * FROM logs WHERE user = ?1 ORDER BY ?2 ?3", nativeQuery = true)
    fun findByUser(user: String, orderBy: String, orderType: String): List<LogEntity>

    @Query("SELECT * FROM logs WHERE ticket = ?1 ORDER BY ?2 ?3", nativeQuery = true)
    fun findByTicket(ticket: UUID, orderBy: String, orderType: String): List<LogEntity>
}