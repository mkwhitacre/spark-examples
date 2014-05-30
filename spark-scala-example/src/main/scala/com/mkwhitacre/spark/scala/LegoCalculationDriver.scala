package com.mkwhitacre.spark.scala

import org.apache.spark.{SparkConf, SparkContext}
import com.mkwhitacre.spark.model.LegoSet._

  //View the README on the proper ways to execute this application
  object LegoCalculationDriver {
    def main(args: Array[String]) {
      val conf = new SparkConf().setAppName("Lego Calculation Spark Scala")
      val spark = new SparkContext(conf)
      val rawRDD = spark.textFile(args(1))

      val filteredLegos = rawRDD.filter(s => !s.startsWith("SetID") || s.trim().length == 0).map(s => parse(s)).filter(l => l.getUsPrice > -1).filter(l => l.getPieces > 0)

      val calculatedPrice = filteredLegos.map(l => (l, l.getUsPrice/l.getPieces))

      //with the given dataset there are too many values to truly print so just pull off the first 10.
      calculatedPrice.take(10);

      spark.stop()
    }
  }

