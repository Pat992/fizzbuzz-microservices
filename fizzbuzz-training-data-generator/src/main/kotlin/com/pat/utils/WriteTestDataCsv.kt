package com.pat.utils

import java.io.File

fun writeTestDataCsv(pathAndName: String, fizzBuzzList: List<Map<Int, String>>) =
    File(pathAndName).printWriter().use { out ->
        fizzBuzzList.forEach { fizzBuzz ->
            out.println("${fizzBuzz.keys.firstOrNull()},${fizzBuzz.entries.firstOrNull()}")
        }
    }