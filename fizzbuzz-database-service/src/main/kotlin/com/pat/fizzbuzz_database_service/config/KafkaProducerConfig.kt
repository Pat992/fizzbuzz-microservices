package com.pat.fizzbuzz_database_service.config

import com.pat.config.buildTopic
import com.pat.config.producerConfigs
import com.pat.properties.KafkaTopics
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.util.*

@Configuration
class KafkaProducerConfig {
    @Bean
    fun producerFactory(): ProducerFactory<UUID, Any> = DefaultKafkaProducerFactory(producerConfigs())

    @Bean
    fun kafkaTemplate(): KafkaTemplate<UUID, Any> = KafkaTemplate(producerFactory())

    @Bean
    fun buildDatabaseEventTopic(): NewTopic = buildTopic(KafkaTopics.DATABASE_EVENT_TOPIC)
}