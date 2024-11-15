package com.pat.fizzbuzz_database_service.consumer

import com.pat.dto.commands.DatabaseUpdateCommand
import com.pat.fizzbuzz_database_service.service.DatabaseCommandService
import com.pat.properties.KafkaTopics
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
@KafkaListener(topics = [KafkaTopics.DATABASE_COMMAND_TOPIC])
class CommandConsumer(private val databaseCommandService: DatabaseCommandService) {

    @KafkaHandler
    fun databaseUpdateCommandHandler(@Payload databaseUpdateCommand: DatabaseUpdateCommand) {
        databaseCommandService.createOrUpdateEntry(databaseUpdateCommand)
    }
}