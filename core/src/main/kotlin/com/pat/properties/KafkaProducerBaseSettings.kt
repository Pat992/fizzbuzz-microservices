package com.pat.properties

import org.apache.kafka.common.serialization.UUIDSerializer
import org.springframework.kafka.support.serializer.JsonSerializer

data object KafkaProducerBaseSettings {
    const val BOOTSTRAP_SERVERS = "localhost:9092,localhost:9094,localhost:9096"
    const val ACKS_CONFIG = "all"
    const val DELIVERY_TIMEOUT_MS_CONFIG = 120000
    const val LINGER_MS_CONFIG = 0
    const val REQ_TIMEOUT_MS_CONFIG = 30000
    const val IN_FLIGHT_REQ_PER_CONN_CONFIG = 5
    const val ENABLE_IDEMPOTENCE_CONFIG = true
    val KEY_SERIALIZER = UUIDSerializer::class.java
    val VALUE_SERIALIZER = JsonSerializer::class.java
}

