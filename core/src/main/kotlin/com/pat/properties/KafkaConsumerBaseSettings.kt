package com.pat.properties

import org.apache.kafka.common.serialization.UUIDDeserializer
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

data object KafkaConsumerBaseSettings {
    const val BOOTSTRAP_SERVERS = "localhost:9092,localhost:9094,localhost:9096"
    const val TRUSTED_PACKAGES = "com.pat.*"
    val KEY_DESERIALIZER = UUIDDeserializer::class.java
    val VALUE_DESERIALIZER = ErrorHandlingDeserializer::class.java
    val ERROR_DESERIALIZER_VALUE = JsonDeserializer::class.java
}