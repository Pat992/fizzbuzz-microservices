package com.pat.properties

data object KafkaConsumerGroups {
    const val ORCHESTRATION_GROUP = "orchestration-ms"
    const val TRANSFORMATION_GROUP = "transform-ms"
    const val DATABASE_GROUP = "database-ms"
    const val LOGGING_GROUP = "logging-ms"
}