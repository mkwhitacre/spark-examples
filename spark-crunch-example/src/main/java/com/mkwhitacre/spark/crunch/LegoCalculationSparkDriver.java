package com.mkwhitacre.spark.crunch;

import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.spark.SparkPipeline;


public class LegoCalculationSparkDriver {

    public static void main(String[] args){

        String sparkUrl = args[0];
        String fileUrl = args[1];

        Pipeline p = new SparkPipeline(sparkUrl, "Lego Calculation");

        LegoCalculationPipeline calculations = new LegoCalculationPipeline(p);

        PipelineResult run = calculations.run(fileUrl);

    }
}
