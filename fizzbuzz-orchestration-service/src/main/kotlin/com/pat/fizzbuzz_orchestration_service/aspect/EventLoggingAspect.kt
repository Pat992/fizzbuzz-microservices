package com.pat.fizzbuzz_orchestration_service.aspect

import com.pat.dto.commands.LogCreateCommand
import com.pat.dto.events.*
import com.pat.properties.KafkaTopics
import com.pat.types.FizzBuzzStatus
import org.apache.kafka.clients.producer.ProducerRecord
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

private const val AOPPackageName = "com.pat.fizzbuzz_orchestration_service.aspect.PointcutDeclarations"

@Aspect
@Component
class EventLoggingAspect(private val kafkaTemplate: KafkaTemplate<UUID, Any>) {

    @Around("$AOPPackageName.databaseUpdateFailedEventHandlerPointcut()")
    fun aroundDatabaseUpdateFailedEventHandlerAdvice(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        val databaseUpdateFailedEvent = proceedingJoinPoint.args.first() as DatabaseUpdateFailedEvent
        val logCreateCommand = databaseUpdateFailedEvent.toLogCreateCommand(FizzBuzzStatus.SAVED_TO_DATABASE_FAILED)

        return baseAroundAspect(proceedingJoinPoint, logCreateCommand)
    }

    @Around("$AOPPackageName.databaseUpdateSuccessEventHandlerPointcut()")
    fun aroundDatabaseUpdateSuccessEventHandlerAdvice(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        val databaseUpdateSuccessEvent = proceedingJoinPoint.args.first() as DatabaseUpdateSuccessEvent
        val logCreateCommand = databaseUpdateSuccessEvent.toLogCreateCommand(FizzBuzzStatus.SAVED_TO_DATABASE_SUCCESS)

        return baseAroundAspect(proceedingJoinPoint, logCreateCommand)
    }

    @Around("$AOPPackageName.requestEventHandlerPointcut()")
    fun requestEventHandlerAdvice(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        val fizzBuzzRequestEvent = proceedingJoinPoint.args.first() as FizzBuzzRequestEvent
        val logCreateCommand = fizzBuzzRequestEvent.toLogCreateCommand(FizzBuzzStatus.REQUEST_RECEIVED_SUCCESS)

        return baseAroundAspect(proceedingJoinPoint, logCreateCommand)
    }

    @Around("$AOPPackageName.transformFailedEventHandlerPointcut()")
    fun aroundTransformFailedEventHandlerAdvice(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        val transformFailedEvent = proceedingJoinPoint.args.first() as FizzBuzzTransformFailedEvent
        val logCreateCommand = transformFailedEvent.toLogCreateCommand(FizzBuzzStatus.TRANSFORM_FAILED)

        return baseAroundAspect(proceedingJoinPoint, logCreateCommand)
    }

    @Around("$AOPPackageName.transformSuccessEventHandlerPointcut()")
    fun aroundTransformSuccessEventHandlerAdvice(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        val transformSuccessEvent = proceedingJoinPoint.args.first() as FizzBuzzTransformSuccessEvent
        val logCreateCommand = transformSuccessEvent.toLogCreateCommand(FizzBuzzStatus.TRANSFORM_SUCCESS)

        return baseAroundAspect(proceedingJoinPoint, logCreateCommand)
    }

    private fun baseAroundAspect(proceedingJoinPoint: ProceedingJoinPoint, logCreateCommand: LogCreateCommand): Any? {
        var res: Any? = null
        try {
            val producerRecord = ProducerRecord(
                KafkaTopics.LOGGING_COMMAND_TOPIC,
                logCreateCommand.ticket,
                logCreateCommand as Any
            )
            kafkaTemplate.send(producerRecord)
            res = proceedingJoinPoint.proceed()
        } catch (e: Exception) {
            println(e)

            val logCreateCommandException =
                logCreateCommand.copy(
                    status = FizzBuzzStatus.ORCHESTRATION_SERVICE_FAILED,
                    message = e.localizedMessage,
                    packageName = this.javaClass.name
                )
            val producerRecordException = ProducerRecord(
                KafkaTopics.LOGGING_COMMAND_TOPIC,
                logCreateCommand.ticket,
                logCreateCommandException as Any
            )
            kafkaTemplate.send(producerRecordException)
        }

        return res
    }
}