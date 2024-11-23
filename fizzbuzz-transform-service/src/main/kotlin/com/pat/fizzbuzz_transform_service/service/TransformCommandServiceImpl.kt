package com.pat.fizzbuzz_transform_service.service

import com.pat.dto.commands.FizzBuzzTransformCommand
import com.pat.dto.commands.toFizzBuzzTransformFailedEvent
import com.pat.dto.commands.toFizzBuzzTransformSuccessEvent
import com.pat.properties.KafkaTopics
import com.pat.rules.FizzBuzzTransformRules
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

@Service
class TransformCommandServiceImpl(private val kafkaTemplate: KafkaTemplate<UUID, Any>) : TransformCommandService {
    override fun transformNumberToFizzBuzz(transformCommand: FizzBuzzTransformCommand) {
        try {
            val output = transformByRules(transformCommand.inputNumber)
            val transformSuccessEvent = transformCommand.toFizzBuzzTransformSuccessEvent(
                OffsetDateTime.now(),
                "Transformation successful - ticket: ${transformCommand.ticket}, user: ${transformCommand.user}",
                output,
                this.javaClass.name
            )
            val producerRecord = ProducerRecord(
                KafkaTopics.TRANSFORM_EVENT_TOPIC,
                transformSuccessEvent.ticket,
                transformSuccessEvent as Any
            )

            kafkaTemplate.send(producerRecord)

        } catch (e: Exception) {
            val transformFailedEvent =
                transformCommand.toFizzBuzzTransformFailedEvent(
                    OffsetDateTime.now(),
                    e.localizedMessage,
                    this.javaClass.name
                )
            val producerRecordException = ProducerRecord(
                KafkaTopics.TRANSFORM_EVENT_TOPIC,
                transformFailedEvent.ticket,
                transformFailedEvent as Any
            )

            kafkaTemplate.send(producerRecordException)
        }
    }

    private fun transformByRules(inputNumber: Int): String {
        var output = ""
        for (transformRule in FizzBuzzTransformRules.entries) {
            if (inputNumber % transformRule.value == 0) output += transformRule.result
        }

        return output.ifEmpty { "$inputNumber" }
    }
}