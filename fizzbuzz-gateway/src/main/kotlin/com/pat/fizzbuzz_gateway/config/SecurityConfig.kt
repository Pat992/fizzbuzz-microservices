package com.pat.fizzbuzz_gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
class SecurityConfig {

    @Bean
    fun userDetailsManager(datasource: DataSource): UserDetailsManager = JdbcUserDetailsManager(datasource)

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain = httpSecurity
        .authorizeHttpRequests {
            it.requestMatchers(
                "/swagger-ui/**",
                "/swagger-resources/*",
                "/v3/api-docs/**"
            ).permitAll()
            it.requestMatchers(HttpMethod.GET, "/api/v1/fizzbuzz/**").hasAnyRole("EMPLOYEE", "ADMIN")
            it.requestMatchers(HttpMethod.POST, "/api/v1/fizzbuzz/**").hasAnyRole("EMPLOYEE", "ADMIN")
            it.requestMatchers(HttpMethod.GET, "/api/v1/log/**").hasAnyRole("ADMIN")
        }
        .httpBasic(Customizer.withDefaults())
        .csrf(Customizer { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() })
        .build()
}