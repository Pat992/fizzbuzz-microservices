package com.pat

import com.pat.data.*
import com.pat.learning.createLogisticRegressionModel
import org.apache.spark.sql.SparkSession

const val testDataPathAndName = "./test-data/fizzbuzz.csv"

fun main() {
    // If this is not set, the whole thing might crash for Windows users
    System.setProperty("hadoop.home.dir", "c:/hadoop")

    SparkSession.builder()
        .appName("fizzbuzz-ml")
        .master("local[*]")
        .getOrCreate()
        .use { spark ->
            registerFizzBuzzToNumberUdf(spark)
            registerFizzBuzzToArrayUdf(spark)
            val dataset = createLearningData(spark, testDataPathAndName)
            val modelDataset = createModelData(spark, dataset)
            val (trainingAndTestData, holdoutData) = splitLearningData(modelDataset)
            val lrModel = createLogisticRegressionModel(trainingAndTestData, holdoutData)
        }
}