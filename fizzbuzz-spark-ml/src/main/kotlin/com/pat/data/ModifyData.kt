package com.pat.data

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.callUDF
import org.apache.spark.sql.functions.col

fun createLearningData(spark: SparkSession, pathAndFilename: String): Dataset<Row> = spark.read()
    .options(mapOf("inferSchema" to "true", "header" to "true"))
    .csv(pathAndFilename)
    .withColumn("fizz_buzz_number", callUDF("fizzBuzzToNumber", col("fizz_buzz")))
    .withColumn("fizz_buzz_array", callUDF("fizzBuzzToArray", col("fizz_buzz")))
    .withColumns(
        mapOf(
            "num" to col("fizz_buzz_array").getItem(0),
            "fizz" to col("fizz_buzz_array").getItem(1),
            "buzz" to col("fizz_buzz_array").getItem(2),
            "fizzbuzz" to col("fizz_buzz_array").getItem(3)
        )
    )
    .drop("fizz_buzz_array")
    .drop("fizz_buzz")

fun splitLearningData(dataset: Dataset<Row>): Array<Dataset<Row>> = dataset.randomSplit(doubleArrayOf(0.9, 0.1))

fun createModelData(spark: SparkSession, dataset: Dataset<Row>): Dataset<Row> = VectorAssembler()
    .setInputCols(arrayOf("input_number", "num", "fizz", "buzz", "fizzbuzz"))
    .setOutputCol("features")
    .transform(dataset)
    .withColumn("label", col("fizz_buzz_number"))
    .select("label", "features")