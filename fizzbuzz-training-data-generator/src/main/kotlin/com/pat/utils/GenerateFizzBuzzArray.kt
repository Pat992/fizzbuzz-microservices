package com.pat.utils

fun generateFizzBuzzArray(numberAmount: Int): List<Map<Int, String>> {
    val fizzBuzzList: MutableList<Map<Int, String>> = mutableListOf()
    for (i in 1..numberAmount) {
        fizzBuzzList.add(mapOf(i to i.toFizzBuzz()))
    }

    return fizzBuzzList
}

fun Int.toFizzBuzz(): String {
    var out = ""
    if (this % 3 == 0) out += "fizz"
    if (this % 5 == 0) out += "buzz"

    return out.ifEmpty { this.toString() }
}