package com.pat.config

import com.pat.properties.KafkaConsumerBaseSettings
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

fun consumerConfigs(groupId: String): Map<String, Any> = mapOf(
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to KafkaConsumerBaseSettings.BOOTSTRAP_SERVERS,
    ConsumerConfig.GROUP_ID_CONFIG to groupId,
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to KafkaConsumerBaseSettings.KEY_DESERIALIZER,
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to KafkaConsumerBaseSettings.VALUE_DESERIALIZER,
    ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to KafkaConsumerBaseSettings.ERROR_DESERIALIZER_VALUE,
    JsonDeserializer.TRUSTED_PACKAGES to KafkaConsumerBaseSettings.TRUSTED_PACKAGES
)