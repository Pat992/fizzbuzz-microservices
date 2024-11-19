package com.pat.fizzbuzz_logging_service.middlware

import com.pat.properties.MicroservicesIpConfig
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.util.matcher.IpAddressMatcher
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class IpWhitelistFilter : OncePerRequestFilter() {

    private fun matches(request: HttpServletRequest, subnet: String): Boolean {
        val ipAddressMatcher = IpAddressMatcher(subnet)
        return ipAddressMatcher.matches(request)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (matches(request, MicroservicesIpConfig.GATEWAY_IP_ADDRESS)) {
            filterChain.doFilter(request, response)
        }
    }
}