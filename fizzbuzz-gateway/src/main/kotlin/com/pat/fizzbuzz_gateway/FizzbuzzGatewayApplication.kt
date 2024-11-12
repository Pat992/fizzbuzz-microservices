package com.pat.fizzbuzz_gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FizzbuzzGatewayApplication

fun main(args: Array<String>) {
	runApplication<FizzbuzzGatewayApplication>(*args)
}
