package com.mkwhitacre.spark.crunch;

import org.apache.crunch.FilterFn;

public class FilterHeaderLineFn extends FilterFn<String> {
    @Override
    public boolean accept(final String input) {
        return !input.startsWith("SetID") || input.trim().length() == 0;
    }
}
