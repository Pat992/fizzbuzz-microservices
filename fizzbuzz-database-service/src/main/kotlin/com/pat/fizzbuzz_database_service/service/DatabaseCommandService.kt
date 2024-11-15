package com.pat.fizzbuzz_database_service.service

import com.pat.dto.commands.DatabaseUpdateCommand

interface DatabaseCommandService {
    fun createOrUpdateEntry(databaseUpdateCommand: DatabaseUpdateCommand)
}