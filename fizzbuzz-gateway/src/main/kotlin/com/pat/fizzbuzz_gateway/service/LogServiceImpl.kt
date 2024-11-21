package com.pat.fizzbuzz_gateway.service

import com.pat.dto.web.LogRequestDto
import com.pat.dto.web.LogResponseDto
import com.pat.properties.MicroservicesIpConfig
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class LogServiceImpl(private val restTemplate: RestTemplate) : LogService {
    override fun getLogs(logRequestDto: LogRequestDto): LogResponseDto? = try {
        restTemplate.getForObject<LogResponseDto>(
            "${MicroservicesIpConfig.LOGGING_SERVICE_URL}/api/v1/log" +
                    "?orderBy=${logRequestDto.orderBy.value}" +
                    "&direction=${logRequestDto.orderDirection.value}" +
                    logRequestDto.user?.let { "&user=$it" } +
                    logRequestDto.ticket?.let { "&ticket=$it" }
        )
    } catch (e: HttpClientErrorException) {
        null
    }
}