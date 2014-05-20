package com.mkwhitacre.spark.crunch;


import com.mkwhitacre.spark.model.LegoSet;
import org.apache.crunch.PCollection;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.io.From;
import org.apache.crunch.types.PType;
import org.apache.crunch.types.avro.Avros;

import java.util.Iterator;

public class LegoCalculationPipeline {

    private final Pipeline pipeline;

    public LegoCalculationPipeline(Pipeline pipeline){
        this.pipeline = pipeline;
    }

    public PipelineResult run(String filePath){

        PCollection<String> raw = pipeline.read(From.textFile(filePath));
        PCollection<String> filteredRaw = raw.filter(new FilterHeaderLineFn());

        PType<LegoSet> legoPType = Avros.reflects(LegoSet.class);

        PCollection <LegoSet> legoSets = filteredRaw.parallelDo(new ParseLegoSetFn(), legoPType);

        PCollection<LegoSet> validDataSets = legoSets.filter(new FilterMissingUSPriceFn()).filter(new FilterMissingPiecesFn());

        PCollection<Pair<LegoSet,Double>> calculatedData = validDataSets.parallelDo(new CalculatePricePerPieceFn(), Avros.pairs(legoPType, Avros.doubles()));

        for (final Pair<LegoSet, Double> next : calculatedData.materialize()) {
            System.out.println("Set: " + next.first().getName() + " Cost Per Piece:" + next.second());
        }

        return pipeline.done();
    }


}
