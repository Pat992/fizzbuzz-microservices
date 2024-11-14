package com.pat.fizzbuzz_orchestration_service.config

import com.pat.config.buildKafkaListenerContainerFactory
import com.pat.config.consumerConfigs
import com.pat.properties.KafkaConsumerGroups
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import java.util.*

@Configuration
class KafkaConsumerConfig() {

    @Bean
    fun kafkaConsumerFactory(): ConsumerFactory<UUID, Any> =
        DefaultKafkaConsumerFactory(consumerConfigs(KafkaConsumerGroups.ORCHESTRATION_GROUP))


    @Bean
    fun kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<UUID, Any>,
        kafkaTemplate: KafkaTemplate<UUID, Any>
    ): ConcurrentKafkaListenerContainerFactory<UUID, Any> =
        buildKafkaListenerContainerFactory(consumerFactory, kafkaTemplate)
}