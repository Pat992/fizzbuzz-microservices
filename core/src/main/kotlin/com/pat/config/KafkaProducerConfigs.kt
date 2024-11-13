package com.pat.config

import com.pat.properties.KafkaProducerBaseSettings
import org.apache.kafka.clients.producer.ProducerConfig

fun producerConfigs(): Map<String, Any> = mapOf(
    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to KafkaProducerBaseSettings.BOOTSTRAP_SERVERS,
    ProducerConfig.ACKS_CONFIG to KafkaProducerBaseSettings.ACKS_CONFIG,
    ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG to KafkaProducerBaseSettings.DELIVERY_TIMEOUT_MS_CONFIG,
    ProducerConfig.LINGER_MS_CONFIG to KafkaProducerBaseSettings.LINGER_MS_CONFIG,
    ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG to KafkaProducerBaseSettings.REQ_TIMEOUT_MS_CONFIG,
    ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION to KafkaProducerBaseSettings.IN_FLIGHT_REQ_PER_CONN_CONFIG,
    ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to KafkaProducerBaseSettings.ENABLE_IDEMPOTENCE_CONFIG,
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to KafkaProducerBaseSettings.KEY_SERIALIZER,
    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to KafkaProducerBaseSettings.VALUE_SERIALIZER,
)