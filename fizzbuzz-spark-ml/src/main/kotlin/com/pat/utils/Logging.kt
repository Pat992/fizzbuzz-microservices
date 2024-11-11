package com.pat.utils

fun logInit(title: String, description: String) = println(
    """
    --------------------------------------------------------------
    ==================>>> $title 

    $description
""".trimIndent()
)

fun logComplete(description: String) = println(
    """
    
    $description
    --------------------------------------------------------------
""".trimIndent()
)