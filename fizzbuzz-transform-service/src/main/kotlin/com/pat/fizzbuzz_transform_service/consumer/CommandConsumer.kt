package com.pat.fizzbuzz_transform_service.consumer

import com.pat.dto.commands.FizzBuzzTransformCommand
import com.pat.fizzbuzz_transform_service.service.TransformCommandService
import com.pat.properties.KafkaTopics
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
@KafkaListener(topics = [KafkaTopics.TRANSFORM_COMMAND_TOPIC])
class CommandConsumer(private val transformCommandService: TransformCommandService) {

    @KafkaHandler
    fun transformCommandHandler(@Payload transformCommand: FizzBuzzTransformCommand) {
        transformCommandService.transformNumberToFizzBuzz(transformCommand)
    }
}