package com.pat.fizzbuzz_gateway.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {
    @Bean
    fun baseRestTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()
}