package com.pat

import com.pat.utils.generateFizzBuzzArray
import com.pat.utils.writeTestDataCsv

const val testDataPathAndName = "./test-data/fizzbuzz.csv"

fun main() = writeTestDataCsv(testDataPathAndName, generateFizzBuzzArray(1000))