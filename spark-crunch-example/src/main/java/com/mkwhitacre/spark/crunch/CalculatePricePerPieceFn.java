package com.mkwhitacre.spark.crunch;

import com.mkwhitacre.spark.model.LegoSet;
import org.apache.crunch.MapFn;
import org.apache.crunch.Pair;

public class CalculatePricePerPieceFn extends MapFn<LegoSet, Pair<LegoSet, Double>> {
    @Override
    public Pair<LegoSet, Double> map(final LegoSet input) {
        return new Pair(input, input.getUsPrice()/input.getPieces());
    }
}
