package com.pat.learning

import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.regression.LinearRegressionModel
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row

fun createLogisticRegressionModel(trainingAndTestDataset: Dataset<Row>): LinearRegressionModel {
    val linearRegression = LinearRegression().setRegParam(0.0).setElasticNetParam(0.0)
    val lrModel = linearRegression.fit(trainingAndTestDataset)

    return lrModel
}