package com.pat.fizzbuzz_orchestration_service.consumer

import com.pat.dto.commands.FizzBuzzTransformCommand
import com.pat.dto.events.*
import com.pat.properties.KafkaTopics
import com.pat.types.DatabaseTypes
import com.pat.types.FizzBuzzStatus
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.*

@Component
@KafkaListener(topics = [KafkaTopics.GATEWAY_EVENT_TOPIC, KafkaTopics.DATABASE_EVENT_TOPIC, KafkaTopics.TRANSFORM_EVENT_TOPIC])
class EventConsumer(private val kafkaTemplate: KafkaTemplate<UUID, Any>) {

    @KafkaHandler
    fun databaseUpdateFailedEventHandler(@Payload databaseUpdateFailedEvent: DatabaseUpdateFailedEvent) = Unit

    @KafkaHandler
    fun databaseUpdateSuccessEventHandler(@Payload databaseUpdateSuccessEvent: DatabaseUpdateSuccessEvent) {
        if (databaseUpdateSuccessEvent.type == DatabaseTypes.CREATE) {
            val transformCommand = FizzBuzzTransformCommand(
                databaseUpdateSuccessEvent.ticket,
                databaseUpdateSuccessEvent.user,
                databaseUpdateSuccessEvent.inputNumber
            )
            val producerRecord = ProducerRecord(
                KafkaTopics.TRANSFORM_COMMAND_TOPIC,
                databaseUpdateSuccessEvent.ticket,
                transformCommand as Any,
            )

            kafkaTemplate.send(producerRecord)
        }
    }

    @KafkaHandler
    fun requestEventHandler(@Payload requestEvent: FizzBuzzRequestEvent) {
        val databaseUpdateCommand = requestEvent.toDatabaseUpdateCommand(
            null,
            null,
            FizzBuzzStatus.REQUEST_RECEIVED_SUCCESS,
            DatabaseTypes.CREATE,
        )
        val producerRecord = ProducerRecord(
            KafkaTopics.DATABASE_COMMAND_TOPIC,
            databaseUpdateCommand.ticket,
            databaseUpdateCommand as Any
        )

        kafkaTemplate.send(producerRecord)
    }

    @KafkaHandler
    fun transformFailedEventHandler(@Payload transformFailedEvent: FizzBuzzTransformFailedEvent) {
        val databaseUpdateCommand = transformFailedEvent.toDatabaseUpdateCommand(
            null,
            null,
            FizzBuzzStatus.TRANSFORM_FAILED,
            DatabaseTypes.UPDATE,
        )
        val producerRecord = ProducerRecord(
            KafkaTopics.DATABASE_COMMAND_TOPIC,
            databaseUpdateCommand.ticket,
            databaseUpdateCommand as Any
        )

        kafkaTemplate.send(producerRecord)
    }

    @KafkaHandler
    fun transformSuccessEventHandler(@Payload transformSuccessEvent: FizzBuzzTransformSuccessEvent) {
        val databaseUpdateCommand = transformSuccessEvent.toDatabaseUpdateCommand(
            null,
            null,
            FizzBuzzStatus.TRANSFORM_SUCCESS,
            DatabaseTypes.UPDATE,
        )
        val producerRecord = ProducerRecord(
            KafkaTopics.DATABASE_COMMAND_TOPIC,
            databaseUpdateCommand.ticket,
            databaseUpdateCommand as Any
        )

        kafkaTemplate.send(producerRecord)
    }
}