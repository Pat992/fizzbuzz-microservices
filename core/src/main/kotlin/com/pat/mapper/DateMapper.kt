package com.pat.mapper

import java.sql.Timestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset

fun OffsetDateTime.toSqlTimestamp(): Timestamp =
    Timestamp.valueOf(this.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())

fun Timestamp.toOffsetDateTime(): OffsetDateTime = OffsetDateTime.of(
    this.toLocalDateTime().year, this.toLocalDateTime().monthValue,
    this.toLocalDateTime().dayOfMonth, this.toLocalDateTime().hour, this.toLocalDateTime().minute,
    this.toLocalDateTime().second, this.toLocalDateTime().nano, ZoneOffset.UTC
)