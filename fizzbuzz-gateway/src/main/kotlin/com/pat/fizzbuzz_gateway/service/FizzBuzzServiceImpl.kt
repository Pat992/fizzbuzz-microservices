package com.pat.fizzbuzz_gateway.service

import com.pat.dto.events.FizzBuzzRequestEvent
import com.pat.dto.web.FizzBuzzRequestDto
import com.pat.dto.web.FizzBuzzResponseDto
import com.pat.dto.web.FizzBuzzResultResponseDto
import com.pat.properties.KafkaTopics
import com.pat.properties.MicroservicesIpConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.security.Principal
import java.time.OffsetDateTime
import java.util.*

@Service
class FizzBuzzServiceImpl(
    private val kafkaTemplate: KafkaTemplate<UUID, FizzBuzzRequestEvent>,
    private val restTemplate: RestTemplate,
) : FizzBuzzService {

    override fun fizzBuzzCreateRequest(request: FizzBuzzRequestDto, principal: Principal): FizzBuzzResponseDto {
        val ticket = UUID.randomUUID()
        val fizzBuzzRequestEvent = FizzBuzzRequestEvent(
            ticket,
            principal.name,
            request.number,
            this.javaClass.name,
            OffsetDateTime.now(),
            "Request received successfully."
        )

        val producerRecord = ProducerRecord(
            KafkaTopics.GATEWAY_EVENT_TOPIC,
            ticket,
            fizzBuzzRequestEvent
        )

        kafkaTemplate.send(producerRecord)

        return FizzBuzzResponseDto(fizzBuzzRequestEvent.ticket, fizzBuzzRequestEvent.eventCreatedAt)
    }

    override fun fizzBuzzGetResult(ticket: UUID, principal: Principal): FizzBuzzResultResponseDto =
        restTemplate.getForObject("${MicroservicesIpConfig.DATABASE_SERVICE_URL}/api/v1/result/$ticket/${principal.name}")
}