package com.mkwhitacre.spark.crunch;

import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.spark.SparkPipeline;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;


public class LegoCalculationSparkDriver {

    public static void main(String[] args){

        String fileUrl = args[0];

        JavaSparkContext context = new JavaSparkContext();

        Pipeline p = new SparkPipeline(context, "Lego Calculation");

        LegoCalculationPipeline calculations = new LegoCalculationPipeline(p);

        PipelineResult run = calculations.run(fileUrl);

        if(run.succeeded()){
          System.out.println("The Spark Run was successful.");
        }else{
          System.out.println("The Spark Run was NOT successful.");
        }
    }
}
