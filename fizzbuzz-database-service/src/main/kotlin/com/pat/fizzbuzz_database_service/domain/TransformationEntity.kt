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
    @Column
    val user: String = "",

    @Id
    @Column
    val ticket: UUID = UUID.randomUUID(),

    @Column
    val inputNumber: Int = 0,

    @Column
    var result: String? = null,

    @Column
    val requestCreatedAt: Timestamp? = null,

    @Column
    var requestCompletedAt: Timestamp? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var status: FizzBuzzStatus = FizzBuzzStatus.REQUEST_RECEIVED_SUCCESS,
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

fun TransformationEntity.toUpdateEntity(existingEntity: TransformationEntity) = TransformationEntity(
    existingEntity.user,
    existingEntity.ticket,
    existingEntity.inputNumber,
    this.result,
    existingEntity.requestCreatedAt,
    this.requestCompletedAt,
    this.status
)