package com.pat.fizzbuzz_database_service.config

import com.pat.properties.MicroservicesIpConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry
                .requestMatchers("/api/**")
                .access(WebExpressionAuthorizationManager("hasIpAddress('${MicroservicesIpConfig.GATEWAY_IP_ADDRESS}')"))
                .anyRequest().authenticated()
        }
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
        return http.build()
    }
}