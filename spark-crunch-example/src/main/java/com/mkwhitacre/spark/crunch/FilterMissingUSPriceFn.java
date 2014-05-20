package com.mkwhitacre.spark.crunch;

import com.mkwhitacre.spark.model.LegoSet;
import org.apache.crunch.FilterFn;

public class FilterMissingUSPriceFn extends FilterFn<LegoSet> {
    @Override
    public boolean accept(final LegoSet input) {
        return input.getUsPrice() > -1;
    }
}
