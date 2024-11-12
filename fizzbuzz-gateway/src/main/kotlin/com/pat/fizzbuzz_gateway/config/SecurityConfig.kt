package com.pat.fizzbuzz_gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
class SecurityConfig {

    @Bean
    fun userDetailsManager(datasource: DataSource): UserDetailsManager = JdbcUserDetailsManager(datasource)

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain = httpSecurity
        .authorizeHttpRequests {
            it.requestMatchers(HttpMethod.GET, "/api").hasAnyRole("EMPLOYEE")
        }
        .httpBasic(Customizer.withDefaults())
        .csrf(Customizer { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() })
        .build()
}