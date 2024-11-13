package com.pat.properties

data object KafkaEvents {
    const val REQUEST_EVENT = "request-event"
    const val TRANSFORM_SUCCESS_EVENT = "transform-success-event"
    const val TRANSFORM_FAILED_EVENT = "transform-failed-event"
    const val DATABASE_UPDATE_SUCCESS_EVENT = "database-update-success-event"
    const val DATABASE_UPDATE_FAILED_EVENT = "database-update-failed-event"
}