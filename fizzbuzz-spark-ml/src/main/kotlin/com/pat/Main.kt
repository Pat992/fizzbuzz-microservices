package com.pat

import com.pat.data.createLearningData
import com.pat.data.createModelData
import com.pat.data.registerUserDefinedFunctions
import com.pat.data.splitLearningData
import com.pat.learning.createLogisticRegressionModel
import com.pat.utils.logComplete
import com.pat.utils.logInit
import org.apache.spark.sql.SparkSession

const val testDataPathAndName = "./test-data/fizzbuzz.csv"

/*
============= How to run:

1. Add the following VM options:
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.invoke=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.base/java.net=ALL-UNNAMED
--add-opens=java.base/java.nio=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED
--add-opens=java.base/sun.nio.ch=ALL-UNNAMED
--add-opens=java.base/sun.nio.cs=ALL-UNNAMED
--add-opens=java.base/sun.security.action=ALL-UNNAMED
--add-opens=java.base/sun.util.calendar=ALL-UNNAMED

2. Make sure it runs on the JVM 17 or older, unfortunately Spark does not support newer versions as of yet.
*/

fun main() {
    // If this is not set, the whole thing might crash for Windows users
    System.setProperty("hadoop.home.dir", "c:/hadoop")

    SparkSession.builder()
        .appName("fizzbuzz-ml")
        .master("local[*]")
        .getOrCreate()
        .use { spark ->
            logInit("User defined functions", "Registering fizzBuzzToNumber & fizzBuzzToArray")
            registerUserDefinedFunctions(spark)
            logComplete("DONE")

            logInit("Dataset", "Creating learning dataset")
            val dataset = createLearningData(spark, testDataPathAndName)
            logComplete("DONE")

            logInit("Model-Dataset", "Creating model dataset")
            val modelDataset = createModelData(spark, dataset)
            logComplete("DONE")

            logInit("Training- and holdout-datasets", "Splitting model-dataset into training and holdout data")
            val (trainingAndTestData, holdoutData) = splitLearningData(modelDataset)
            logComplete("DONE")

            logInit("Linear regression model", "Initializing and configure linear regression model")
            val lrModel = createLogisticRegressionModel(trainingAndTestData)
            logComplete("DONE")

            logInit("Holdout data", "Testing holdout data on the linear regression model")
            lrModel.transform(holdoutData).show(100)
            logComplete("DONE")
        }
}