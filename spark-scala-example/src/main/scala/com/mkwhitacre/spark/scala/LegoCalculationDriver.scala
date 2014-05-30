package com.mkwhitacre.spark.scala

import org.apache.spark.{SparkConf, SparkContext}
import com.mkwhitacre.spark.model.LegoSet._

  /** Computes an approximation to pi */
  object LegoCalculationDriver {
    def main(args: Array[String]) {
      val master = args(0)
      val conf = new SparkConf()
      val spark = new SparkContext(master, "Lego Calculation Spark Scala", conf)
      val rawRDD = spark.textFile(args(1))

      val filteredLegos = rawRDD.filter(s => !s.startsWith("SetID") || s.trim().length == 0).map(s => parse(s)).filter(l => l.getUsPrice > -1).filter(l => l.getPieces > 0)

      val calculatedPrice = filteredLegos.map(l => (l, l.getUsPrice/l.getPieces))

      calculatedPrice.collect()

      spark.stop()
    }
  }

