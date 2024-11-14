package com.pat.config

import com.pat.exceptions.NotRetryableException
import com.pat.exceptions.RetryableException
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.util.backoff.FixedBackOff
import java.util.*

fun buildKafkaConsumerDefaultErrorHandler(kafkaTemplate: KafkaTemplate<UUID, Any>): DefaultErrorHandler {
    val defaultErrorHandler = DefaultErrorHandler(
        DeadLetterPublishingRecoverer(kafkaTemplate),
        FixedBackOff(5000, 3)
    )

    defaultErrorHandler.addNotRetryableExceptions(NotRetryableException::class.java)
    defaultErrorHandler.addRetryableExceptions(RetryableException::class.java)

    return defaultErrorHandler
}