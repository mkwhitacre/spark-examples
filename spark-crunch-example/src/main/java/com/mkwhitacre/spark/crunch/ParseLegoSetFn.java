package com.mkwhitacre.spark.crunch;

import com.mkwhitacre.spark.model.LegoSet;
import org.apache.crunch.MapFn;

public class ParseLegoSetFn extends MapFn<String, LegoSet> {
    @Override
    public LegoSet map(final String input) {
        return LegoSet.parse(input);
    }
}
