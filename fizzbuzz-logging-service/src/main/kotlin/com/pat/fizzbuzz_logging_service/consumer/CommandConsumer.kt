package com.pat.fizzbuzz_logging_service.consumer

import com.pat.dto.commands.LogCreateCommand
import com.pat.fizzbuzz_logging_service.service.LogCommandService
import com.pat.properties.KafkaTopics
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
@KafkaListener(topics = [KafkaTopics.LOGGING_COMMAND_TOPIC])
class CommandConsumer(private val logCommandService: LogCommandService) {

    @KafkaHandler
    fun logCreateCommandHandler(@Payload logCreateCommand: LogCreateCommand) {
        logCommandService.saveLog(logCreateCommand)
    }
}