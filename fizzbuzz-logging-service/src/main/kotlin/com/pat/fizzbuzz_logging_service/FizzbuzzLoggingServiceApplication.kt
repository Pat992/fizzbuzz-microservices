package com.pat.fizzbuzz_logging_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FizzbuzzLoggingServiceApplication

fun main(args: Array<String>) {
	runApplication<FizzbuzzLoggingServiceApplication>(*args)
}
