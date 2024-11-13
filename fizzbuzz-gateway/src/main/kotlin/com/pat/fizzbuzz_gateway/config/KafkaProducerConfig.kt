package com.pat.fizzbuzz_gateway.config

import com.pat.config.buildTopic
import com.pat.config.producerConfigs
import com.pat.dto.events.FizzBuzzRequestEvent
import com.pat.properties.KafkaTopics
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.util.*

@Configuration
class KafkaProducerConfig {

    @Bean
    fun producerFactory(): ProducerFactory<UUID, FizzBuzzRequestEvent> = DefaultKafkaProducerFactory(producerConfigs())

    @Bean
    fun kafkaTemplate(): KafkaTemplate<UUID, FizzBuzzRequestEvent> = KafkaTemplate(producerFactory())

    @Bean
    fun buildGatewayEventTopic() = buildTopic(KafkaTopics.GATEWAY_EVENT_TOPIC)
}