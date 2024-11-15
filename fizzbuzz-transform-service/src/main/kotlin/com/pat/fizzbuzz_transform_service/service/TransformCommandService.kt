package com.pat.fizzbuzz_transform_service.service

import com.pat.dto.commands.FizzBuzzTransformCommand

interface TransformCommandService {
    fun transformNumberToFizzBuzz(transformCommand: FizzBuzzTransformCommand)
}