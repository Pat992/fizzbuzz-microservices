package com.pat.config

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import java.util.*

fun buildKafkaListenerContainerFactory(
    consumerFactory: ConsumerFactory<UUID, Any>,
    kafkaTemplate: KafkaTemplate<UUID, Any>
): ConcurrentKafkaListenerContainerFactory<UUID, Any> {
    val defaultErrorHandler = buildKafkaConsumerDefaultErrorHandler(kafkaTemplate)

    val factory = ConcurrentKafkaListenerContainerFactory<UUID, Any>()
    factory.consumerFactory = consumerFactory
    factory.setCommonErrorHandler(defaultErrorHandler)

    return factory
}