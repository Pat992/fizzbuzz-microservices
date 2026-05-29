package com.pat.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.kafka.config.TopicBuilder

const val TOPIC_REPLICATION_FACTOR = 3
const val TOPIC_PARTITIONS = 3

fun buildTopic(topicName: String): NewTopic = TopicBuilder
    .name(topicName)
    .partitions(TOPIC_PARTITIONS)
    .replicas(TOPIC_REPLICATION_FACTOR)
    .build()