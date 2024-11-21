package com.pat.fizzbuzz_logging_service.domain

import com.pat.dto.commands.LogCreateCommand
import com.pat.dto.web.LogDto
import com.pat.dto.web.LogResponseDto
import com.pat.mapper.toOffsetDateTime
import com.pat.mapper.toSqlTimestamp
import com.pat.types.FizzBuzzStatus
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(name = "logs")
class LogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val user: String = "",

    @Column
    val ticket: UUID = UUID.randomUUID(),

    @Column
    val inputNumber: Int = 0,

    @Column
    val packageName: String = "",

    @Column
    @Enumerated(EnumType.STRING)
    val status: FizzBuzzStatus = FizzBuzzStatus.COMPLETED,

    @Column
    val message: String = "",

    @Column
    val updatedAt: Timestamp? = null,
)

fun LogCreateCommand.toLogEntity() = LogEntity(
    user = this.user,
    ticket = this.ticket,
    inputNumber = this.inputNumber,
    packageName = this.packageName,
    status = this.status,
    message = this.message,
    updatedAt = this.updatedAt.toSqlTimestamp()
)

fun List<LogEntity>.toLogResponseDto(): LogResponseDto {
    val logResponseDto = LogResponseDto(mutableListOf())
    for (logEntity in this) {
        logResponseDto.logs.add(logEntity.toLogDto())
    }

    return logResponseDto
}

fun LogEntity.toLogDto(): LogDto = LogDto(
    this.ticket,
    this.user,
    this.inputNumber,
    this.packageName,
    this.status,
    this.message,
    this.updatedAt?.toOffsetDateTime() ?: OffsetDateTime.now()
)