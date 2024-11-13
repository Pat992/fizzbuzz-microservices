package com.pat.properties

data object KafkaTopics {
    const val GATEWAY_EVENT_TOPIC = "gateway-event-topic"
    const val TRANSFORM_EVENT_TOPIC = "transform-event-topic"
    const val DATABASE_EVENT_TOPIC = "database-event-topic"

    const val TRANSFORM_COMMAND_TOPIC = "transform-command-topic"
    const val DATABASE_COMMAND_TOPIC = "database-command-topic"
    const val LOGGING_COMMAND_TOPIC = "logging-command-topic"
}