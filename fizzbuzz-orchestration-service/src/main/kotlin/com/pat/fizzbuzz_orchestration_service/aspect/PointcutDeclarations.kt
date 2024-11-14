package com.pat.fizzbuzz_orchestration_service.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

private const val consumerPackageName = "com.pat.fizzbuzz_orchestration_service.consumer.EventConsumer"

@Aspect
class PointcutDeclarations {
    @Pointcut("execution(* $consumerPackageName.databaseUpdateFailedEventHandler(..))")
    fun databaseUpdateFailedEventHandlerPointcut() = Unit

    @Pointcut("execution(* $consumerPackageName.databaseUpdateSuccessEventHandler(..))")
    fun databaseUpdateSuccessEventHandlerPointcut() = Unit

    @Pointcut("execution(* $consumerPackageName.requestEventHandler(..))")
    fun requestEventHandlerPointcut() = Unit

    @Pointcut("execution(* $consumerPackageName.transformFailedEventHandler(..))")
    fun transformFailedEventHandlerPointcut() = Unit

    @Pointcut("execution(* $consumerPackageName.transformSuccessEventHandler(..))")
    fun transformSuccessEventHandlerPointcut() = Unit
}