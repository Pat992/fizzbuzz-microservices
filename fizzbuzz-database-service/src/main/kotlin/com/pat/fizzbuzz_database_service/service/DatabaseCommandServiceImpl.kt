package com.pat.fizzbuzz_database_service.service

import com.pat.dto.commands.DatabaseUpdateCommand
import com.pat.dto.commands.toDatabaseUpdateFailedEvent
import com.pat.dto.commands.toDatabaseUpdateSuccessEvent
import com.pat.exceptions.EntryAlreadyExistsException
import com.pat.exceptions.EntryDoesNotExistException
import com.pat.fizzbuzz_database_service.domain.TransformationCompositeKey
import com.pat.fizzbuzz_database_service.domain.TransformationEntity
import com.pat.fizzbuzz_database_service.domain.toTransformationEntity
import com.pat.fizzbuzz_database_service.domain.toUpdateEntity
import com.pat.fizzbuzz_database_service.repository.TransformationRepository
import com.pat.properties.KafkaTopics
import com.pat.types.DatabaseTypes
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

@Service
class DatabaseCommandServiceImpl(
    private val kafkaTemplate: KafkaTemplate<UUID, Any>,
    private val transformationRepository: TransformationRepository
) : DatabaseCommandService {

    override fun createOrUpdateEntry(databaseUpdateCommand: DatabaseUpdateCommand) {
        try {
            val transformationEntity = databaseUpdateCommand.toTransformationEntity(
                databaseUpdateCommand.type != DatabaseTypes.CREATE,
                if (databaseUpdateCommand.type == DatabaseTypes.CREATE) null else OffsetDateTime.now(),
            )
            if (databaseUpdateCommand.type == DatabaseTypes.CREATE
            ) {
                createEntry(transformationEntity)
            } else {
                updateEntry(transformationEntity)
            }
            val databaseUpdateSuccessEvent = databaseUpdateCommand.toDatabaseUpdateSuccessEvent(
                this.javaClass.name,
                OffsetDateTime.now(),
                if (databaseUpdateCommand.type == DatabaseTypes.CREATE)
                    "Entry created successfully - ticket: ${databaseUpdateCommand.ticket}, user: ${databaseUpdateCommand.user}"
                else
                    "Entry updated successfully - ticket: ${databaseUpdateCommand.ticket}, user: ${databaseUpdateCommand.user}",
                databaseUpdateCommand.result ?: ""
            )
            val producerRecord = ProducerRecord(
                KafkaTopics.DATABASE_EVENT_TOPIC,
                databaseUpdateSuccessEvent.ticket,
                databaseUpdateSuccessEvent as Any
            )

            kafkaTemplate.send(producerRecord)
        } catch (e: Exception) {
            println(e)
            val databaseUpdateFailedEvent = databaseUpdateCommand.toDatabaseUpdateFailedEvent(
                this.javaClass.name,
                OffsetDateTime.now(),
                e.localizedMessage
            )
            val producerRecordException = ProducerRecord(
                KafkaTopics.DATABASE_EVENT_TOPIC,
                databaseUpdateFailedEvent.ticket,
                databaseUpdateFailedEvent as Any
            )

            kafkaTemplate.send(producerRecordException)
        }
    }

    private fun createEntry(transformationEntity: TransformationEntity): TransformationEntity {
        if (transformationRepository.existsById(
                TransformationCompositeKey(
                    transformationEntity.user,
                    transformationEntity.ticket
                )
            )
        ) {
            throw EntryAlreadyExistsException("Entry for user: ${transformationEntity.user} and ticket: ${transformationEntity.ticket} already exists.")
        }

        return transformationRepository.save(transformationEntity)
    }

    private fun updateEntry(transformationEntity: TransformationEntity): TransformationEntity {
        val existingEntity = transformationRepository.findById(
            TransformationCompositeKey(
                transformationEntity.user,
                transformationEntity.ticket
            )
        ).orElseThrow {
            EntryDoesNotExistException("Entry for user: ${transformationEntity.user} and ticket: ${transformationEntity.ticket} does not exist.")
        }

        return transformationRepository.save(transformationEntity.toUpdateEntity(existingEntity))
    }
}