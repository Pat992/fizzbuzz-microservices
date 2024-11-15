package com.pat.fizzbuzz_database_service.domain

import com.pat.dto.commands.DatabaseUpdateCommand
import com.pat.mapper.toSqlTimestamp
import com.pat.types.FizzBuzzStatus
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(name = "transformations")
@IdClass(TransformationCompositeKey::class)
class TransformationEntity(
    @Id
    val user: String,
    @Id
    val ticket: UUID,
    @Column
    val inputNumber: Int,
    @Column
    var result: String?,
    @Column
    val requestCreatedAt: Timestamp,
    @Column
    var requestCompletedAt: Timestamp?,
    @Column
    var status: FizzBuzzStatus
)

fun DatabaseUpdateCommand.toTransformationEntity(isCompleted: Boolean = false, completedDate: OffsetDateTime? = null) =
    TransformationEntity(
        this.user,
        this.ticket,
        this.inputNumber,
        this.result,
        this.requestCreatedAt.toSqlTimestamp(),
        if (isCompleted) completedDate?.toSqlTimestamp() else this.requestCompletedAt?.toSqlTimestamp(),
        this.status
    )

