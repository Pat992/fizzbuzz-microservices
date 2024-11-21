package com.pat.fizzbuzz_database_service.service

import com.pat.dto.web.FizzBuzzResultResponseDto
import com.pat.fizzbuzz_database_service.domain.toResultResponseDto
import com.pat.fizzbuzz_database_service.repository.TransformationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ResultServiceImpl(private val transformationRepository: TransformationRepository) : ResultService {
    override fun getResultByTicketAndUsername(ticket: UUID, username: String): FizzBuzzResultResponseDto? =
        transformationRepository.findByUserAndTicket(username, ticket)?.toResultResponseDto()
}