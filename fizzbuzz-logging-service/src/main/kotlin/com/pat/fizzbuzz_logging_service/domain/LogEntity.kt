package com.pat.fizzbuzz_logging_service.domain

import com.pat.dto.commands.LogCreateCommand
import com.pat.mapper.toSqlTimestamp
import com.pat.types.FizzBuzzStatus
import jakarta.persistence.*
import java.sql.Timestamp
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