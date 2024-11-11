package com.pat.utils

import java.io.File

fun writeTestDataCsv(pathAndFilename: String, fizzBuzzList: List<Map<Int, String>>) =
    File(pathAndFilename).printWriter().use { out ->
        out.println("input_number,fizz_buzz")
        fizzBuzzList.forEach { fizzBuzz ->
            val key = fizzBuzz.keys.firstOrNull()
            out.println("$key,${fizzBuzz[key]}")
        }
    }