package com.pat.fizzbuzz_gateway.service

import com.pat.dto.web.FizzBuzzRequestDto
import com.pat.dto.web.FizzBuzzResponseDto
import com.pat.dto.web.FizzBuzzResultResponseDto
import com.pat.types.FizzBuzzStatus
import org.springframework.stereotype.Service
import java.security.Principal
import java.time.OffsetDateTime
import java.util.*

@Service
class FizzBuzzServiceImpl : FizzBuzzService {
    override fun fizzBuzzCreateRequest(request: FizzBuzzRequestDto, principal: Principal): FizzBuzzResponseDto =
        FizzBuzzResponseDto(UUID.randomUUID(), OffsetDateTime.now())

    override fun fizzBuzzGetResult(ticket: UUID, principal: Principal): FizzBuzzResultResponseDto =
        FizzBuzzResultResponseDto(
            "1",
            1,
            ticket,
            OffsetDateTime.now(),
            OffsetDateTime.now(),
            FizzBuzzStatus.COMPLETED,
            false,
            null
        )
}