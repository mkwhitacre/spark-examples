package com.mkwhitacre.spark.crunch;

import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class LegoCalculationHadoopDriver implements Tool {

    private Configuration config;

    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new LegoCalculationHadoopDriver(), args);
        System.exit(result);
    }

    @Override
    public void setConf(Configuration conf) {
        config = conf;
    }

    @Override
    public Configuration getConf() {
        return config;
    }
    @Override
    public int run(final String[] args) throws Exception {
        Pipeline pipeline = new MRPipeline(LegoCalculationHadoopDriver.class, getConf());

        String filePath = args[0];

        LegoCalculationPipeline calculations = new LegoCalculationPipeline(pipeline);
        PipelineResult run = calculations.run(filePath);

        return run.succeeded() ? 0 : 1;
    }

}
