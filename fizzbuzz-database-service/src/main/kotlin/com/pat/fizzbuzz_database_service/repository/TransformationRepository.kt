package com.pat.fizzbuzz_database_service.repository

import com.pat.fizzbuzz_database_service.domain.TransformationCompositeKey
import com.pat.fizzbuzz_database_service.domain.TransformationEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TransformationRepository : JpaRepository<TransformationEntity, TransformationCompositeKey> {
    fun findByUserAndTicket(user: String, ticket: UUID): TransformationEntity?
}