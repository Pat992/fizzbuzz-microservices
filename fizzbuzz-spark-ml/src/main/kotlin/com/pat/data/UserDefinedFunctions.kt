package com.pat.data

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.api.java.UDF1
import org.apache.spark.sql.types.DataTypes

fun registerFizzBuzzToNumberUdf(spark: SparkSession): Unit =
    spark.udf().register("fizzBuzzToNumber",
        UDF1 { fizzBuzz: String ->
            when (fizzBuzz) {
                "fizz" -> 100
                "buzz" -> 200
                "fizzbuzz" -> 300
                else -> 0
            }
        } as UDF1<String, Int>,
        DataTypes.IntegerType
    )

fun registerFizzBuzzToArrayUdf(spark: SparkSession): Unit =
    spark.udf().register("fizzBuzzToArray",
        UDF1 { fizzBuzz: String ->
            when (fizzBuzz) {
                "fizz" -> listOf(0, 1, 0, 0)
                "buzz" -> listOf(0, 0, 1, 0)
                "fizzbuzz" -> listOf(0, 0, 0, 1)
                else -> listOf(1, 0, 0, 0)
            }
        } as UDF1<*, *>,
        DataTypes.createArrayType(DataTypes.IntegerType)
    )