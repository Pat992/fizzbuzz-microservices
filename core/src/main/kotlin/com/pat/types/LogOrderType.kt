package com.pat.types

enum class LogOrderType(val value: String) {
    USER("user"), TICKET("ticket"), DATE("updated_at")
}

enum class LogOrderDirection(val value: String) {
    ASC("ASC"), DESC("DESC")
}